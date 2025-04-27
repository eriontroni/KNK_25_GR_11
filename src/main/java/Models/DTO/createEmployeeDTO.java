package Models.DTO;

import java.time.LocalDate;

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
    private LocalDate hire_date;

    public createEmployeeDTO(String first_name, String last_name, String position, String email, String phone, LocalDate hire_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.hire_date = hire_date;
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
    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }
}
