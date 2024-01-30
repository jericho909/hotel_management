package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance = null;

    private Connection connection = null;

    private final String DB_URL= "jdbc:postgresql://localhost:5432/hotel";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "postgres";
    //for creating the connection to the db
    public DbConnection() {
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            System.out.println("Connected to database.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
    //if the connection is closed or something went wrong, we ensure that a new connection is generated
    public static Connection getInstance(){
        try {
            if (instance == null || instance.getConnection().isClosed()){
                instance = new DbConnection();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }
}
