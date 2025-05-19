package Models.DTO;

import java.util.Date;

//CREATE TABLE Employee (
  //      id SERIAL PRIMARY KEY,
    //    first_name VARCHAR(100) NOT NULL,
//last_name VARCHAR(100) NOT NULL,
//position VARCHAR(100),
//email VARCHAR(255) UNIQUE,
//phone VARCHAR(20),
//hire_date DATE
//);
public class createEmployeeDTO {
    private String first_name;
    private String last_name;
    private String position;
    private String email;
    private String phone;
    private String password_hash;
    private String salted_hash;

    public createEmployeeDTO(String first_name, String last_name, String position, String email,String phone,String password_hash,String salted_hash) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.email = email;
        this.password_hash = password_hash;
        this.salted_hash = salted_hash;
        this.phone = phone;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getSalted_hash() {
        return salted_hash;
    }

    public void setSalted_hash(String salted_hash) {
        this.salted_hash = salted_hash;
    }

}
