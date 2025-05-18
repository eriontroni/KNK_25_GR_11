package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class ReceptionDashboardController {

    @FXML
    private void handlePerRole(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ChartPerRole.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Numri i përdoruesve për çdo rol");
        stage.show();
    }

    @FXML
    private void handlePerDay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ChartPerDay.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Numri i rezervimeve për çdo ditë");
        stage.show();
    }
}
