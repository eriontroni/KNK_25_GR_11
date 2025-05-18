package Services;

import DataBase.DBConnector;
import Models.Customer;
import Models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public Boolean emailExists(String email, String tablename) {
        Connection connection = DBConnector.getConnection();
        String sql = "SELECT 1 FROM " + tablename + " WHERE email = ? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next(); // nëse ka ndonjë rresht, ekziston
            }
        } catch (Exception ex) {
            return false;
        }
    }
}
