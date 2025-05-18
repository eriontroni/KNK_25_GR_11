package Controllers;

import Models.DTO.createEmployeeDTO;
import Models.Employee;
import Services.PasswordHasher;
import Services.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import repository.EmployeeRepository;

import java.sql.Date;

public class SignupStafiController {
    @FXML
    public TextField signupName;
    @FXML
    public TextField signupSurname;
    @FXML
    public TextField signupEmail;
    @FXML
    public PasswordField signupPass;
    @FXML
    private ChoiceBox<String> positionBox;
    @FXML
    public TextField nrTel;
    public Date hireDate;

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void handleSignup(ActionEvent actionEvent) {
        String name = signupName.getText();
        String surname = signupSurname.getText();
        String position = positionBox.getValue();
        String email = signupEmail.getText();
        String pass = signupPass.getText();
        String phone = nrTel.getText();

        // Email format validation
        if (!Validator.isValidEmail(email)) {
            showAlert("Formati i email-it është i pavlefshëm!");
            return;
        }
        // Password format validation
        if (!Validator.isValidPassword(pass)) {
            showAlert("Passwordi duhet të ketë të paktën 8 karaktere, një shkronjë të madhe, një të vogël, një numër dhe një simbol.");
            return;
        }
        if(position == null){
            showAlert("Duhet te zgjedhesh poziten!");
        }

        String salt = PasswordHasher.generateSalt();
        String password_hash = PasswordHasher.generateSaltedHash(pass, salt);
        createEmployeeDTO emp = new createEmployeeDTO(name,surname,email,position,password_hash,salt,phone);
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = employeeRepository.create(emp);
        if (employee != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Employee hired!");
            alert.showAndWait();
        } else {
            showAlert("Gabim gjatë krijimit të userit!");
        }
    }
}
