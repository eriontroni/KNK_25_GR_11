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


    public Button loginButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();
        Stage stage = (Stage) loginButton.getScene().getWindow();


        if (LoginService.emailExists(email, "Employee")) {

            if (LoginService.checkPassword(email, password, "Employee")) {

                if (LoginService.positionExists(email, "Receptionist")) {

                    EmployeeRepository er = new EmployeeRepository();
                    Employee employee = er.getByEmail(email);
                    EmployeeSessionManager employeeSessionManager = new EmployeeSessionManager();
                    employeeSessionManager.setCurrentUser(employee);

                    // TODO: Me shku te faqja e recepcionistit DONE
                    SceneManager.switchScene(stage, SceneLocator.Reception_Dashboard_Page, "ReceptionDashboard");
                } else if (LoginService.positionExists(email, "Maintanance")) {

                    EmployeeRepository er = new EmployeeRepository();
                    Employee employee = er.getByEmail(email);
                    EmployeeSessionManager employeeSessionManager = new EmployeeSessionManager();
                    employeeSessionManager.setCurrentUser(employee);

                    SceneManager.switchScene(stage, SceneLocator.Maintenance_Page, "Maintanance");
                    // TODO: Me shku te faqja e maintanancewerq DONE
                } else {
                    showAlert("Passwordi i gabuar!");
                }
            } else if (LoginService.emailExists(email, "Users")) {
                if (LoginService.checkPassword(email, password, "Users")) {

                    showAlert("JENI QASUR!");
                    UserSessionManager userSessionManager = UserSessionManager.getInstance();
                    UsersRepository ur = new UsersRepository();
                    Users user = ur.getByEmail(email);
                    userSessionManager.setCurrentUser(user);

                    //TODO: Me shku te faqja e Userit apo klientit DONE
                    SceneManager.switchScene(stage, SceneLocator.Home_Page, "ClientHome");
                } else {
                    showAlert("Passwordi i gabuar!");
                }
            } else {
                showAlert("Passwordi dhe Emaili nuk u pershtaten");
            }
        }

    }
    public void goBack(ActionEvent actionEvent){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        SceneManager.switchScene(stage, SceneLocator.First_Page, "First Page");
    }
}
