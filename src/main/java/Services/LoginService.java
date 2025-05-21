package Services;

import DataBase.DBConnector;
import Models.Customer;
import Models.Users;
import repository.UsersRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public static Boolean emailExists(String email, String tablename) {
        if (email == null || email.trim().isEmpty()) return false;
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
    public static Boolean positionExists(String email, String position) {
        Connection connection = DBConnector.getConnection();
        String query = "SELECT 1 FROM employee WHERE email = ? AND position = ? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, position);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public static Boolean checkPassword(String email, String password, String tablename ) {
        Connection connection = DBConnector.getConnection();
        String query = "SELECT password_hash, salted_hash FROM " + tablename + " WHERE email = ? LIMIT 1";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String dbHash = rs.getString("password_hash");
                    String dbSalt = rs.getString("salted_hash");
                    // password (nga parametri), dbSalt (nga db), dbHash (nga db)
                    return PasswordHasher.compareSaltedHash(password, dbSalt, dbHash);
                }
            }
        } catch (Exception ex) {
            System.out.println("gabim brenda try te checkPassword");
            ex.printStackTrace();
        }
        return false;
    }

}
