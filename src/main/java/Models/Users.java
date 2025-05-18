package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    private int id;
    private String username;
    private String email;
    private String password_hash;
    private String salted_hash;

    

    private Users(int id, String username, String email, String password_hash,String salted_hash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.salted_hash = salted_hash;
        
    }

    public static Users getInstance(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String password_hash = rs.getString("password_hash");
        String salted_hash = rs.getString("salted_hash");
        return new Users(id, username, email, password_hash,salted_hash);
    }

    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public String getpassword_hash() { return this.password_hash; }

    public String getsalted_hash() {
        return this.salted_hash;
    }
}
