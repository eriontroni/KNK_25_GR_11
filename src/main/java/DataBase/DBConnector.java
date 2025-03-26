package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
    private static final String DB_URL = "jdbc:postgresql://localhost/knk_projekt";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1212";

    private static Connection connection = null;
    public static Connection getConnection() {
        if(connection == null) {
            try{
                connection = DriverManager.getConnection(
                        DB_URL,
                        DB_USER,
                        DB_PASSWORD
                );
            }catch(SQLException e){
                return null;
            }
        }
        return connection;
    }

}
