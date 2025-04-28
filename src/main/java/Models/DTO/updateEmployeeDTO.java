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
private String name;
private String lastname;
    private String position;//nese kemi rritje ne pozite


    public updateEmployeeDTO( String position, String name, String lastname) {

        this.position = position;
        this.name = name;
        this.lastname = lastname;
    }
  

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
