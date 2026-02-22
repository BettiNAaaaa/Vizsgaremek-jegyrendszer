package security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

public class JwtUtil {

    private static final String SECRET = "vizsgaremek_secret_key_2026";
    private static final long EXP_SECONDS = 60 * 60; // 1 óra

    public static class DecodedToken {
        public final String subject;
        public final String role;

        public DecodedToken(String subject, String role) {
            this.subject = subject;
            this.role = role;
        }
    }

    public static String generateToken(String subject, String role) {
        String headerJson = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        long exp = Instant.now().getEpochSecond() + EXP_SECONDS;
        String payloadJson = "{\"sub\":\"" + escape(subject) + "\",\"role\":\"" + escape(role) + "\",\"exp\":" + exp + "}";

        String header = base64Url(headerJson.getBytes(StandardCharsets.UTF_8));
        String payload = base64Url(payloadJson.getBytes(StandardCharsets.UTF_8));
        String unsigned = header + "." + payload;
        String signature = hmacSha256(unsigned, SECRET);

        return unsigned + "." + signature;
    }

    public static DecodedToken verifyToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) throw new RuntimeException("Invalid JWT format");

        String unsigned = parts[0] + "." + parts[1];
        String expectedSig = hmacSha256(unsigned, SECRET);
        if (!constantTimeEquals(expectedSig, parts[2])) {
            throw new RuntimeException("Invalid JWT signature");
        }

        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);

        String sub = extractJsonString(payloadJson, "sub");
        String role = extractJsonString(payloadJson, "role");
        long exp = extractJsonLong(payloadJson, "exp");

        if (Instant.now().getEpochSecond() > exp) {
            throw new RuntimeException("JWT expired");
        }

        return new DecodedToken(sub, role);
    }

    private static String base64Url(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }

    private static String hmacSha256(String data, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return base64Url(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Minimal JSON extract (flat payload)
    private static String extractJsonString(String json, String key) {
        String pattern = "\"" + key + "\":\"";
        int start = json.indexOf(pattern);
        if (start < 0) return null;
        start += pattern.length();
        int end = json.indexOf("\"", start);
        if (end < 0) return null;
        return json.substring(start, end);
    }

    private static long extractJsonLong(String json, String key) {
        String pattern = "\"" + key + "\":";
        int start = json.indexOf(pattern);
        if (start < 0) return 0;
        start += pattern.length();
        int end = start;
        while (end < json.length() && Character.isDigit(json.charAt(end))) end++;
        return Long.parseLong(json.substring(start, end));
    }

    private static boolean constantTimeEquals(String a, String b) {
        if (a.length() != b.length()) return false;
        int r = 0;
        for (int i = 0; i < a.length(); i++) r |= a.charAt(i) ^ b.charAt(i);
        return r == 0;
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}