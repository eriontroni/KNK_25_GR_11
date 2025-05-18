package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class OffersController {

    @FXML private Button btnBackToHome;
    @FXML private Label lblTitle;
    @FXML private Label currentDateLabel;


    @FXML
    public void initialize() {
        // Initialize UI elements
        lblTitle.setText("Special Offers");
        currentDateLabel.setText(LocalDate.now().toString());

        // Back button action
        btnBackToHome.setOnAction(e -> goBackToHome());
    }

    private void goBackToHome() {
        try {
            Parent homeRoot = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
            Stage stage = (Stage) btnBackToHome.getScene().getWindow();
            stage.setScene(new Scene(homeRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}