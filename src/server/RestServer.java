/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;



import api.AdminApi;
import api.AuthApi;
import api.EventApi;
import api.TicketApi;
import controller.*;
import dao.*;
import model.Booking;
import model.Event;
import model.User;
import security.JwtUtil;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
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

        // Controller
        AuthController authController = new AuthController(userDao);
        AdminController adminController = new AdminController(userDao);
        EventController eventController = new EventController(eventDao);
        TicketController ticketController = new TicketController(eventDao, ticketDao);

        // API
        AuthApi authApi = new AuthApi(authController);
        AdminApi adminApi = new AdminApi(adminController);
        EventApi eventApi = new EventApi(eventController);
        TicketApi ticketApi = new TicketApi(ticketController);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // CORS preflight handler
        server.createContext("/api", exchange -> {
            addCors(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            exchange.sendResponseHeaders(404, -1);
        });

        // POST /api/auth/login
        server.createContext("/api/auth/login", exchange -> {
            addCors(exchange);

            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            String body = readBody(exchange);
            String email = jsonGet(body, "email");
            String password = jsonGet(body, "password");

            try {
                User user = authApi.login(email, password);
                String token = JwtUtil.generateToken(user.getEmail(), user.getRole().name());

                String resp = "{"
                        + "\"token\":\"" + escape(token) + "\","
                        + "\"role\":\"" + user.getRole().name() + "\","
                        + "\"userId\":" + user.getId() + ","
                        + "\"name\":\"" + escape(user.getName()) + "\","
                        + "\"email\":\"" + escape(user.getEmail()) + "\""
                        + "}";

                sendJson(exchange, 200, resp);

            } catch (Exception e) {
                sendJson(exchange, 401, "{\"error\":\"Bad credentials\"}");
            }
        });
    }