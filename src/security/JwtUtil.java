package security;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

public class JwtUtil {

    private static final String SECRET = "vizsgaremek_secret_2026";
    private static final long EXP_SECONDS = 60 * 60; // 1 óra

    public static class DecodedToken {
        public final String subject; // email
        public final String role; // ADMIN/USER

        public DecodedToken(String subject, String role) {
            this.subject = subject;
            this.role = role;
        }
    }

    public static String generateToken(String subject, String role) {
        String headerJson = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        long exp = Instant.now().getEpochSecond() + EXP_SECONDS;
        String payloadJson = "{\"sub\":\"" + esc(subject) + "\",\"role\":\"" + esc(role) + "\",\"exp\":" + exp + "}";

        String header = b64url(headerJson.getBytes(StandardCharsets.UTF_8));
        String payload = b64url(payloadJson.getBytes(StandardCharsets.UTF_8));
        String unsigned = header + "." + payload;
        String sig = hmac(unsigned, SECRET);

        return unsigned + "." + sig;
    }

    public static DecodedToken verifyToken(String token) {
        String[] p = token.split("\\.");
        if (p.length != 3) throw new RuntimeException("Invalid token format");

        String unsigned = p[0] + "." + p[1];
        String expected = hmac(unsigned, SECRET);
        if (!constantTimeEquals(expected, p[2])) throw new RuntimeException("Bad signature");

        String payload = new String(Base64.getUrlDecoder().decode(p[1]), StandardCharsets.UTF_8);

        String sub = jsonStr(payload, "sub");
        String role = jsonStr(payload, "role");
        long exp = jsonLong(payload, "exp");

        if (Instant.now().getEpochSecond() > exp) throw new RuntimeException("Token expired");

        return new DecodedToken(sub, role);
    }

    private static String b64url(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }

    private static String hmac(String data, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return b64url(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean constantTimeEquals(String a, String b) {
        if (a.length() != b.length()) return false;
        int r = 0;
        for (int i = 0; i < a.length(); i++) r |= a.charAt(i) ^ b.charAt(i);
        return r == 0;
    }

    private static String jsonStr(String json, String key) {
        String p = "\"" + key + "\":\"";
        int i = json.indexOf(p);
        if (i < 0) return null;
        i += p.length();
        int e = json.indexOf("\"", i);
        return (e < 0) ? null : json.substring(i, e);
    }

    private static long jsonLong(String json, String key) {
        String p = "\"" + key + "\":";
        int i = json.indexOf(p);
        if (i < 0) return 0;
        i += p.length();
        int e = i;
        while (e < json.length() && Character.isDigit(json.charAt(e))) e++;
        return Long.parseLong(json.substring(i, e));
    }

    private static String esc(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}