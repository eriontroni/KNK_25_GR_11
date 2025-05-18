package Controllers;
import Services.LoginService;
import Services.PasswordHasher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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




        if(LoginService.emailExists(email,"Employee")) {

            if (LoginService.checkPassword(email,password,"Employee")) {

                if (LoginService.positionExists(email, "Receptionist")) {
                    // TODO: Me shku te faqja e recepcionistit

                } else if (LoginService.positionExists(email, "Maintanance")) {
                    // TODO: Me shku te faqja e maintanance


                }
            }else{
                showAlert("Passwordi i gabuar!");
            }
        }else if(LoginService.emailExists(email,"Users")){
            if(LoginService.checkPassword(email,password,"Users")) {

                //TODO: Me shku te faqja e Userit apo klientit
            }else{
                showAlert("Passwordi i gabuar!");
            }
        }
    }
}
