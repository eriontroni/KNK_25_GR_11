package Controllers;

import Models.Employee;
import Models.Users;
import Services.EmployeeSessionManager;
import Services.LoginService;
import Services.UserSessionManager;
import Utils.SceneLocator;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.EmployeeRepository;
import repository.UsersRepository;

public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    // Shfaq alert për gabime ose informata
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gabim");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        Stage stage = (Stage) loginButton.getScene().getWindow();

        // Validim që të dy fushat janë plotësuar
        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Ju lutem plotësoni të gjitha fushat!");
            return;
        }

        // Kontrollo fillimisht Employee
        if (LoginService.emailExists(email, "Employee")) {
            if (LoginService.checkPassword(email, password, "Employee")) {
                if (LoginService.positionExists(email, "Recepcionist")) {
                    EmployeeRepository er = new EmployeeRepository();
                    Employee employee = er.getByEmail(email);
                    EmployeeSessionManager employeeSessionManager = new EmployeeSessionManager();
                    employeeSessionManager.setCurrentUser(employee);

                    SceneManager.switchScene(stage, SceneLocator.Reception_Dashboard_Page, "ReceptionDashboard");
                } else if (LoginService.positionExists(email, "Mirembajtes")) {
                    EmployeeRepository er = new EmployeeRepository();
                    Employee employee = er.getByEmail(email);
                    EmployeeSessionManager employeeSessionManager = new EmployeeSessionManager();
                    employeeSessionManager.setCurrentUser(employee);
                    SceneManager.switchScene(stage, SceneLocator.Main_Maintenance_Page, "Mirembajtes");
                } else {
                    showAlert("Nuk keni pozitë të përcaktuar të lejuar për qasje.");
                }
            } else {
                showAlert("Passwordi i gabuar!");
            }
            return;
        }

        // Pastaj kontrollo si User (klient)
        if (LoginService.emailExists(email, "Users")) {
            if (LoginService.checkPassword(email, password, "Users")) {
                UsersRepository ur = new UsersRepository();
                Users user = ur.getByEmail(email);
                UserSessionManager userSessionManager = UserSessionManager.getInstance();

                userSessionManager.setCurrentUser(user);
                SceneManager.switchScene(stage, SceneLocator.Home_Page, "ClientHome");
            } else {
                showAlert("Passwordi i gabuar!");
            }
            return;
        }

        // Nëse nuk ekziston as si Employee as si User
        showAlert("Ky email nuk ekziston në sistem!");
    }

    public void goSignup(ActionEvent actionEvent) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        SceneManager.switchScene(stage, SceneLocator.Signup_Page, "Signup");
    }
}
