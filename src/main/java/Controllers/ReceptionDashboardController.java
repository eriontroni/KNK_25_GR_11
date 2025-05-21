package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionDashboardController {

    @FXML
    private void handlePerRole() {
        openWindow("/views/ChartPerRole.fxml", "Numri i përdoruesve për çdo rol");
    }

    @FXML
    private void handleCreateReservation() {
        openWindow("/views/Reservations.fxml", "Krijo Rezervim");
    }

    @FXML
    private void handleAddStaff() {
        openWindow("/views/signupStafi.fxml", "Shto Staf");
    }

    @FXML
    private void handleLogout() {
        openWindow("/views/logini.fxml", "Login");
    }

    // Kjo metodë hap një window të RI për çdo FXML që i thirret
    private void openWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
