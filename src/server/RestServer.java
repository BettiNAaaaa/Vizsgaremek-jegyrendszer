/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;



import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import api.*;
import controller.*;
import dao.*;
import model.*;
import security.JwtUtil;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RestServer {

    public static void main(String[] args) throws Exception {

        // DAO
        UserDao userDao = new UserDao();
        EventDao eventDao = new EventDao();
        TicketDao ticketDao = new TicketDao();
        TheaterDao theaterDao = new TheaterDao();
        MovieDao movieDao = new MovieDao();

        // Controllerek
        AuthController authController = new AuthController(userDao);
        AdminController adminController = new AdminController(userDao);
        EventController eventController = new EventController(eventDao);
        TicketController ticketController = new TicketController(ticketDao);
        TheaterController theaterController = new TheaterController(theaterDao);
        MovieController movieController = new MovieController(movieDao);

        // API 
        AuthApi authApi = new AuthApi(authController);
        AdminApi adminApi = new AdminApi(adminController);
        EventApi eventApi = new EventApi(eventController);
        TicketApi ticketApi = new TicketApi(ticketController);
        TheaterApi theaterApi = new TheaterApi(theaterController);
        MovieApi movieApi = new MovieApi(movieController);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // login
        server.createContext("/api/auth/login", ex -> {
            cors(ex);

            if (isOptions(ex)) return;
            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String body = readBody(ex);
            String email = jsonStr(body, "email");
            String password = jsonStr(body, "password");

            try {
                User u = authApi.login(email, password);
                String token = JwtUtil.generateToken(u.getEmail(), u.getRole().name());

                String resp = "{"
                        + "\"token\":\"" + esc(token) + "\","
                        + "\"role\":\"" + u.getRole().name() + "\","
                        + "\"userId\":" + u.getId() + ","
                        + "\"name\":\"" + esc(u.getName()) + "\","
                        + "\"email\":\"" + esc(u.getEmail()) + "\""
                        + "}";

                sendJson(ex, 200, resp);
            } catch (Exception e) {
                sendJson(ex, 401, "{\"error\":\"Bad credentials\"}");
            }
        });

        // eventek
        server.createContext("/api/events", ex -> {
            cors(ex);
            if (isOptions(ex)) return;

            if (!"GET".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            List<Event> events = eventApi.getAllEvents();
            sendJson(ex, 200, eventsToJson(events));
        });

        // film
        server.createContext("/api/movies", ex -> {
            cors(ex);
            if (isOptions(ex)) return;

            if (!"GET".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            sendJson(ex, 200, eventsToJson(movieApi.getAllMovies()));
        });

        // szinhaz
        server.createContext("/api/theatre", ex -> {
            cors(ex);
            if (isOptions(ex)) return;

            if (!"GET".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            sendJson(ex, 200, theatresToJson(theaterApi.getAllTheatres()));
        });

        // jegylistazas
        server.createContext("/api/tickets", ex -> {
            cors(ex);
            if (isOptions(ex)) return;

            if (!"GET".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            int eventId = queryInt(ex.getRequestURI().getQuery(), "eventId", -1);
            if (eventId <= 0) {
                sendJson(ex, 400, "{\"error\":\"Missing eventId\"}");
                return;
            }

            List<Ticket> tickets = ticketApi.listTicketsForEvent(eventId);
            sendJson(ex, 200, ticketsToJson(tickets));
        });

        // jegyfoglalas
        server.createContext("/api/tickets/reserve", ex -> {
            cors(ex);
            if (isOptions(ex)) return;

            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String token = bearer(ex.getRequestHeaders());
            if (token == null) {
                sendJson(ex, 401, "{\"error\":\"Missing token\"}");
                return;
            }

            try { JwtUtil.verifyToken(token); } catch (Exception e) {
                sendJson(ex, 401, "{\"error\":\"Invalid token\"}");
                return;
            }

            String body = readBody(ex);
            int ticketId = jsonInt(body, "ticketId");
            boolean ok = ticketApi.reserveTicket(ticketId);

            sendJson(ex, ok ? 200 : 400, ok ? "{\"status\":\"reserved\"}" : "{\"error\":\"Cannot reserve\"}");
        });

        // jegyvasarlas
        server.createContext("/api/tickets/buy", ex -> {
            cors(ex);
            if (isOptions(ex)) return;

            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String token = bearer(ex.getRequestHeaders());
            if (token == null) {
                sendJson(ex, 401, "{\"error\":\"Missing token\"}");
                return;
            }

            try { JwtUtil.verifyToken(token); } catch (Exception e) {
                sendJson(ex, 401, "{\"error\":\"Invalid token\"}");
                return;
            }

            String body = readBody(ex);
            int ticketId = jsonInt(body, "ticketId");
            boolean ok = ticketApi.buyTicket(ticketId);

            sendJson(ex, ok ? 200 : 400, ok ? "{\"status\":\"sold\"}" : "{\"error\":\"Cannot buy\"}");
        });

        // lemondas
        server.createContext("/api/tickets/cancel", ex -> {
            cors(ex);
            if (isOptions(ex)) return;

            if (!"POST".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String token = bearer(ex.getRequestHeaders());
            if (token == null) {
                sendJson(ex, 401, "{\"error\":\"Missing token\"}");
                return;
            }

            try { JwtUtil.verifyToken(token); } catch (Exception e) {
                sendJson(ex, 401, "{\"error\":\"Invalid token\"}");
                return;
            }

            String body = readBody(ex);
            int ticketId = jsonInt(body, "ticketId");
            boolean ok = ticketApi.cancelReservation(ticketId);

            sendJson(ex, ok ? 200 : 400, ok ? "{\"status\":\"available\"}" : "{\"error\":\"Cannot cancel\"}");
        });

        // admin torles
        server.createContext("/api/admin/users", ex -> {
            cors(ex);
            if (isOptions(ex)) return;

            if (!"DELETE".equalsIgnoreCase(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String token = bearer(ex.getRequestHeaders());
            if (token == null) {
                sendJson(ex, 401, "{\"error\":\"Missing token\"}");
                return;
            }

            int id = queryInt(ex.getRequestURI().getQuery(), "id", -1);
            if (id <= 0) {
                sendJson(ex, 400, "{\"error\":\"Missing id\"}");
                return;
            }

            try {
                boolean ok = adminApi.deleteUser(token, id);
                sendJson(ex, ok ? 200 : 404, ok ? "{\"status\":\"deleted\"}" : "{\"error\":\"Not found\"}");
            } catch (Exception e) {
                sendJson(ex, 403, "{\"error\":\"" + esc(e.getMessage()) + "\"}");
            }
        });

        server.start();
        System.out.println("REST API fut: http://localhost:8080");
    }


    private static boolean isOptions(HttpExchange ex) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(ex.getRequestMethod())) {
            ex.sendResponseHeaders(204, -1);
            return true;
        }
        return false;
    }

    private static void cors(HttpExchange ex) {
        Headers h = ex.getResponseHeaders();
        h.set("Access-Control-Allow-Origin", "*");
        h.set("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
        h.set("Access-Control-Allow-Headers", "Content-Type,Authorization");
    }

    private static String bearer(Headers headers) {
        String a = headers.getFirst("Authorization");
        if (a == null) return null;
        if (!a.startsWith("Bearer ")) return null;
        return a.substring("Bearer ".length()).trim();
    }

    private static String readBody(HttpExchange ex) throws IOException {
        try (InputStream in = ex.getRequestBody()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private static void sendJson(HttpExchange ex, int code, String json) throws IOException {
        byte[] data = json.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        ex.sendResponseHeaders(code, data.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(data);
        }
    }

    
    private static String jsonStr(String json, String key) {
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

    private static int jsonInt(String json, String key) {
        String p = "\"" + key + "\":";
        int i = json.indexOf(p);
        if (i < 0) return 0;
        i += p.length();
        while (i < json.length() && Character.isWhitespace(json.charAt(i))) i++;
        int e = i;
        while (e < json.length() && (Character.isDigit(json.charAt(e)) || json.charAt(e) == '-')) e++;
        return Integer.parseInt(json.substring(i, e));
    }

    private static int queryInt(String query, String key, int def) {
        if (query == null) return def;
        for (String part : query.split("&")) {
            String[] kv = part.split("=");
            if (kv.length == 2 && kv[0].equals(key)) {
                try { return Integer.parseInt(kv[1]); } catch (Exception ignored) {}
            }
        }
        return def;
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String eventsToJson(List<Event> list) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Event e : list) {
            if (!first) sb.append(",");
            first = false;
            sb.append("{")
              .append("\"id\":").append(e.getId()).append(",")
              .append("\"title\":\"").append(esc(e.getTitle())).append("\",")
              .append("\"room\":\"").append(esc(e.getRoom())).append("\",")
              .append("\"type\":\"").append(esc(e.getType())).append("\",")
              .append("\"start\":\"").append(e.getStart()).append("\",")
              .append("\"end\":\"").append(e.getEnd()).append("\",")
              .append("\"seats\":").append(e.getSeats()).append(",")
              .append("\"cinemaId\":").append(e.getCinemaId() == null ? "null" : e.getCinemaId()).append(",")
              .append("\"theatreId\":").append(e.getTheatreId() == null ? "null" : e.getTheatreId())
              .append("}");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String ticketsToJson(List<Ticket> list) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Ticket t : list) {
            if (!first) sb.append(",");
            first = false;
            sb.append("{")
              .append("\"id\":").append(t.getId()).append(",")
              .append("\"eventId\":").append(t.getEventId()).append(",")
              .append("\"seatLabel\":\"").append(esc(t.getSeatLabel())).append("\",")
              .append("\"price\":").append(t.getPrice()).append(",")
              .append("\"status\":\"").append(esc(t.getStatus())).append("\",")
              .append("\"created\":\"").append(t.getCreated()).append("\"")
              .append("}");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String theatresToJson(List<Theater> list) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Theater t : list) {
            if (!first) sb.append(",");
            first = false;
            sb.append("{")
              .append("\"id\":").append(t.getId()).append(",")
              .append("\"name\":\"").append(esc(t.getName())).append("\",")
              .append("\"city\":\"").append(esc(t.getCity())).append("\",")
              .append("\"address\":\"").append(esc(t.getAddress())).append("\",")
              .append("\"phone\":\"").append(esc(t.getPhone())).append("\",")
              .append("\"website\":\"").append(esc(t.getWebsite())).append("\"")
              .append("}");
        }
        sb.append("]");
        return sb.toString();
    }
}