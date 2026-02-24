package Main;

import server.RestServer;

public class Main {
    public static void main(String[] args) throws Exception {
        RestServer.main(args);
        
        
     try (var c = dao.Database.getConnection()) {
    System.out.println("DB CATALOG = " + c.getCatalog());
} 
    }
    

}