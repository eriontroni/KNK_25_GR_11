package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
        if ("eri".equals(email) && "122".equals(password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ClientHomepage.fxml"));
                Parent root = loader.load();

                System.out.println("FXML loaded me sukses!");

                // Merre kontrolluesin për ta dërguar emrin
                ClientHomepageController controller = loader.getController();
                controller.setEmriKlientit(email);  // dërgo email ose emër


                // Krijo dhe shfaq skenën e re
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Homepage e Klientit");
                stage.show();

                // Mbyll dritaren aktuale (login)
                Stage currentStage = (Stage) emailField.getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
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
