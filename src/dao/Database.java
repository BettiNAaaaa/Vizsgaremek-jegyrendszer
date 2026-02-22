package dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

   
    private static final String DB = "vizsgaremek%20%28ticketz%29";

    
    private static final int PORT = 3306;

    private static final String URL =
            "jdbc:mysql://localhost:" + PORT + "/" + DB + "?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "root"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}