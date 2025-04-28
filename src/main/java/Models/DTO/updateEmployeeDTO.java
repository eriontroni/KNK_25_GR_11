package Models.DTO;


//CREATE TABLE Employee (
//      id SERIAL PRIMARY KEY,
//    first_name VARCHAR(100) NOT NULL,
//last_name VARCHAR(100) NOT NULL,
//position VARCHAR(100),
//email VARCHAR(255) UNIQUE,
//phone VARCHAR(20),
//hire_date DATE
//);

public class updateEmployeeDTO {
    private String first_name;//mund te perdoret nese e shkruajme gabim
    private String last_name;//njejte
    private String position;//nese kemi rritje ne pozite
    private String email;
    private String phone;

    public updateEmployeeDTO(String first_name, String last_name, String position, String email, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.email = email;
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
}
