package testDB;
import DataBase.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test_DB {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnector.getConnection();
        String query = """
INSERT INTO test(first_name,last_name)
VALUES('ERION','TRONI')
                """; // test per me e pa a funksionon databaza;
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet results = statement.executeQuery("SELECT * FROM test");
        while(results.next()){
            System.out.println(
              results.getInt("id")
              + " " +
              results.getString("first_name")
              + " " +
              results.getString("last_name")
              + "------------------------------------------"
            );
        }
    }
}
