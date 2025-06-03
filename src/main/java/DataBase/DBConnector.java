package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String DB_URL = "jdbc:postgresql://localhost/knk_projekt";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123";

    private static Connection connection = null;
    public static Connection getConnection() {
    try {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
    return connection;
}

}
//public static Connection getConnection() {
//    try {
//        if (connection == null || connection.isClosed()) {
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        return null;
//    }
//    return connection;
//}
