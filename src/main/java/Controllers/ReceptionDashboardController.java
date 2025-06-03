package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionDashboardController {

    public Button btnPerRole;

    @FXML
    private void handlePerRole() {
        openWindow("/views/ChartPerRole.fxml", "Numri i përdoruesve për çdo rol");
        closeCurrentWindow();
    }

    @FXML
    private void handleCreateReservation() {

        openWindow("/views/ReservationsReception.fxml", "Krijo Rezervim");
        closeCurrentWindow();
    }

    @FXML
    private void handleAddStaff() {
        openWindow("/views/signupStafi.fxml", "Shto Staf");
        closeCurrentWindow();
    }

    @FXML
    private void handleLogout() {
        openWindow("/views/logini.fxml", "Login");
        closeCurrentWindow();
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
    private void closeCurrentWindow() {
        // Mer referencen nga ndonjë komponent, p.sh. rootPane ose ndonjë buton
        Stage stage = (Stage) btnPerRole.getScene().getWindow();
        stage.close();
    }
}
