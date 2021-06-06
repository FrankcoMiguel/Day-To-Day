package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private final String dbPath = "jdbc:sqlite:storage.db";
    private static Connection connection;

    private DBConnection(){

        try {

            Class.forName("org.sqlite.JDBC");
            getConnection();

        } catch (Exception e){

            System.out.println("Something went wrong \n" + "Check this out: \n" + e);

        }

    }

    public Connection getConnection() throws SQLException {

        if (connection == null){

            connection = DriverManager.getConnection(dbPath);

        }

        return connection;

    }

    public static DBConnection getInstance(){

        if (instance == null){

            instance = new DBConnection();
        }

        return instance;
    }

    public void closeConnection() throws SQLException {

        if (!connection.isClosed()){

            connection.close();

        }

    }


}
