package Controllers;

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

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Zëvendëso këtë pjesë me logjikën e vërtetë të autentikimit
        if ("eri".equals(email) && "122".equals(password)) {
            try {

                System.out.println("FXML loaded me sukses!");





                // Mbyll dritaren aktuale (login)
                Stage currentStage = (Stage) emailField.getScene().getWindow();
                currentStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // kur t deshton
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gabim");
            alert.setHeaderText(null);
            alert.setContentText("Emaili i përdoruesit ose fjalëkalimi është i pasaktë.");
            alert.showAndWait();
        }
    }
}
