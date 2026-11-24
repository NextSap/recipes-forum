package be.lesdatlon.api.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private final String url;
    private final String username;
    private final String password;

    private Database() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.url = System.getenv("DB_URL");
            this.username = System.getenv("DB_USERNAME");
            this.password = System.getenv("DB_PASSWORD");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC Oracle not found", e);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}