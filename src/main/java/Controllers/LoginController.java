package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Zëvendëso këtë pjesë me logjikën e vërtetë të autentikimit
        if ("admin".equals(email) && "password".equals(password)) {
            // kur tesht e suksesshme
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukses");
            alert.setHeaderText(null);
            alert.setContentText("Hyrja u realizua me sukses!");
            alert.showAndWait();
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
