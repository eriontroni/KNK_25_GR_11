package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RoomsController {

    @FXML
    private VBox roomCard101;

    @FXML
    private ImageView roomImage;

    @FXML
    private void handleClick(ActionEvent event) {
        // Mundesh me marrë butonin që u kliku
        Button clickedButton = (Button) event.getSource();

        // Reagim i thjesht për testim
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking");
        alert.setHeaderText(null);
        alert.setContentText("You clicked 'Book Now' for Room 101!");
        alert.showAndWait();
    }
}
