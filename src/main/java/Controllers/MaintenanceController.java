package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class MaintenanceController {

    @FXML
    private TableView<?> maintenanceTable;

    @FXML
    private Button addbutton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    public void initialize() {
        // Initialize button actions
        addbutton.setOnAction(event -> showAlert("Add Button Clicked", "You clicked the Add button"));
        deleteButton.setOnAction(event -> showAlert("Delete Button Clicked", "You clicked the Delete button"));
        editButton.setOnAction(event -> showAlert("Edit Button Clicked", "You clicked the Edit button"));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}