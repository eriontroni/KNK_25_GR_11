package Controllers;

import Utils.SceneLocator;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Main_MaintenanceController {

    @FXML
    private void handleMirembajtje(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.switchScene(stage, SceneLocator.Maintenance_Page, "Mirembajtje");
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.switchScene(stage, "/views/logini.fxml", "Login");
    }

    @FXML
    private void handlePastrim(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.switchScene(stage, "/views/cleaningschedule.fxml", "Pastrim");
    }
}
