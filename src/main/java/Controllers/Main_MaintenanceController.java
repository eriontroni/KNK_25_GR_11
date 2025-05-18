package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_MaintenanceController {

    @FXML
    private void handleMirembajtje(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/maintenance.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Mirembajtje");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBackToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/logini.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Mirembajtje");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handlePastrim(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/cleaningschedule.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Pastrim");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
