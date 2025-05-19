package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;

public class ReservationConfirmedController {

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblRoomInfo;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblStatus;

    @FXML
    private ImageView roomImage;

    public void setReservationData(String room, String price, String imagePath) {
        lblRoomInfo.setText("Dhomë: " + room);
        lblPrice.setText("Çmimi: " + price);
        lblStatus.setText("Statusi: ✅ Rezervuar");

        // Shto image nëse e dërgon pathin
        if (imagePath != null && !imagePath.isEmpty()) {
            Image img = new Image(getClass().getResourceAsStream(imagePath));
            roomImage.setImage(img);
        }
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) lblTitle.getScene().getWindow();
        stage.close();
    }
}
