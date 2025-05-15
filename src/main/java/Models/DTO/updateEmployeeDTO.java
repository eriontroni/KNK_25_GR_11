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
    private int id;
    private String position;
    private String email;
    private String phone;

    public updateEmployeeDTO(int id, String position, String email, String phone) {
        this.id = id;
        this.position = position;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
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
