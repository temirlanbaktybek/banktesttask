package init;

import java.sql.DriverManager;

public class InitializeWorker {

    public static void initializePostgresDatabase(String dbName, String username) throws Exception {
        try (var connection = DriverManager.getConnection("jdbc:postgresql://localhost:10432/", username, "")) {
            try (var statement = connection.createStatement()) {
                statement.execute("create database " + dbName);
                System.out.println("xlCM6GiOXy :: charity DATABASE created!!!");
            }
        }
    }
}
