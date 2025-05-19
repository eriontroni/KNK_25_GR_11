package Models;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String position;
    private String email;
    private String phone;
    private String password_hash;
    private String salted_hash;
    private Date hire_date;

    Employee(int id, String first_name, String last_name, String position, String email, String phone, Date hire_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.hire_date = hire_date;
    }

    public static Employee getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        String position = resultSet.getString("positon");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        
        Date hire_date = resultSet.getDate("hire_date");

        return new Employee(id, first_name, last_name, position, email, phone, hire_date);
    }
    public int getId(){return id;};
    public String getFirst_name() {return first_name;}
    public String getLast_name() {return last_name;}
    public String getPosition() {return position;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public Date getHire_date() {return hire_date;}



}
