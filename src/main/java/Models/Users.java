package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    private int id;
    private String username;
    private String email;
    private String passwordHash;
    private String role;

    private Users(int id, String username, String email, String passwordHash, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public static Users getInstance(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String passwordHash = rs.getString("password_hash");
        String role = rs.getString("role");

        return new Users(id, username, email, passwordHash, role);
    }

    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public String getPasswordHash() { return passwordHash; }

    public String getRole() { return role; }
}
