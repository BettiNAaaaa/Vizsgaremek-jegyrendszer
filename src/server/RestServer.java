/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;







import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dao.UserDao;
import model.User;
import security.JwtUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class RestServer {

    private final UserDao userDao;

    public RestServer() {
        this.userDao = new UserDao();
    }

    public static void main(String[] args) throws Exception {
        new RestServer().start(3306);
    }

    public void start(int port) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Base /api handler (preflight + not found)
        server.createContext("/api", ex -> {
            addCors(ex);
            if ("OPTIONS".equalsIgnoreCase(ex.getRequestMethod())) {
                ex.sendResponseHeaders(204, -1);
                return;
            }
            sendJson(ex, 404, "{\"error\":\"Not found\"}");
        });

        // POST /api/auth/login
        server.createContext("/api/auth/login", ex -> {
            addCors(ex);

            if ("OPTIONS".equalsIgnoreCase(ex.getRequestMethod())) {
                ex.sendResponseHeaders(204, -1);
                return;
            }

            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String body = readBody(ex);
            String email = jsonGet(body, "email");
            String password = jsonGet(body, "password");

            if (email == null || password == null) {
                sendJson(ex, 400, "{\"error\":\"Missing email or password\"}");
                return;
            }

            User user = userDao.findByEmail(email);
            if (user == null || !user.getPassword().equals(password)) {
                sendJson(ex, 401, "{\"error\":\"Bad credentials\"}");
                return;
            }

            String token = JwtUtil.generateToken(user.getEmail(), user.getRole().name());

            String resp = "{"
                    + "\"token\":\"" + esc(token) + "\","
                    + "\"role\":\"" + user.getRole().name() + "\","
                    + "\"userId\":" + user.getId() + ","
                    + "\"name\":\"" + esc(user.getName()) + "\","
                    + "\"email\":\"" + esc(user.getEmail()) + "\""
                    + "}";

            sendJson(ex, 200, resp);
        });

        // DELETE /api/admin/users?id=2 (ADMIN token required)
        server.createContext("/api/admin/users", ex -> {
            addCors(ex);

            if ("OPTIONS".equalsIgnoreCase(ex.getRequestMethod())) {
                ex.sendResponseHeaders(204, -1);
                return;
            }

            if (!"DELETE".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String token = readBearer(ex.getRequestHeaders());
            if (token == null) {
                sendJson(ex, 401, "{\"error\":\"Missing token\"}");
                return;
            }

            JwtUtil.DecodedToken decoded;
            try {
                decoded = JwtUtil.verifyToken(token);
            } catch (Exception e) {
                sendJson(ex, 401, "{\"error\":\"Invalid token\"}");
                return;
            }

            if (!"ADMIN".equals(decoded.role)) {
                sendJson(ex, 403, "{\"error\":\"Forbidden: ADMIN only\"}");
                return;
            }

            int id = queryInt(ex.getRequestURI().getQuery(), "id", -1);
            if (id <= 0) {
                sendJson(ex, 400, "{\"error\":\"Missing or invalid id\"}");
                return;
            }

            boolean ok = userDao.deleteById(id);
            if (ok) sendJson(ex, 200, "{\"status\":\"deleted\"}");
            else sendJson(ex, 404, "{\"error\":\"User not found\"}");
        });

        server.start();
        System.out.println("REST API fut: http://localhost:" + port);
        System.out.println("POST /api/auth/login");
        System.out.println("DELETE /api/admin/users?id=2 (Bearer ADMIN token)");
    }

  

    private static void addCors(HttpExchange ex) {
        Headers h = ex.getResponseHeaders();
        h.set("Access-Control-Allow-Origin", "*");
        h.set("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
        h.set("Access-Control-Allow-Headers", "Content-Type,Authorization");
    }

    private static String readBody(HttpExchange ex) throws IOException {
        try (InputStream in = ex.getRequestBody()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private static void sendJson(HttpExchange ex, int status, String json) throws IOException {
        byte[] data = json.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        ex.sendResponseHeaders(status, data.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(data);
        }
    }

    // Minimal JSON getter: flat JSON {"email":"...","password":"..."}
    private static String jsonGet(String json, String key) {
        if (json == null) return null;

        String p = "\"" + key + "\":";
        int i = json.indexOf(p);
        if (i < 0) return null;
        i += p.length();

        while (i < json.length() && Character.isWhitespace(json.charAt(i))) i++;

        if (i < json.length() && json.charAt(i) == '"') {
            i++;
            int e = json.indexOf('"', i);
            if (e < 0) return null;
            return json.substring(i, e);
        }
        return null;
    }

    private static String readBearer(Headers headers) {
        String auth = headers.getFirst("Authorization");
        if (auth == null) return null;
        if (!auth.startsWith("Bearer ")) return null;
        return auth.substring("Bearer ".length()).trim();
    }

    private static int queryInt(String query, String key, int def) {
        if (query == null) return def;
        for (String part : query.split("&")) {
            String[] kv = part.split("=");
            if (kv.length == 2 && kv[0].equals(key)) {
                try {
                    return Integer.parseInt(kv[1]);
                } catch (Exception ignored) {}
            }
        }
        return def;
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
