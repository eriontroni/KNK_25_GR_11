package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Date;

public class ReservationsController {

    @FXML
    private DatePicker arrivalDate;

    @FXML
    private DatePicker departureDate;

    @FXML
    private ComboBox<String> guestCombo;

    @FXML
    private ComboBox<String> paymentMethod;

    @FXML
    private Button bookButton;

    @FXML
    public void initialize() {
        arrivalDate.setValue(java.time.LocalDate.now());
        departureDate.setValue(java.time.LocalDate.now().plusDays(1));
    }

    @FXML
    private void handleBook() {
        Date arrival = arrivalDate.getValue() != null ? Date.valueOf(arrivalDate.getValue()) : null;
        Date departure = departureDate.getValue() != null ? Date.valueOf(departureDate.getValue()) : null;
        String guests = guestCombo.getValue();
        String payment = paymentMethod.getValue();

        if (arrival == null || departure == null || guests == null || payment == null) {
            showAlert("Ju lutem plotësoni të gjitha fushat.");
            return;
        }

        System.out.println("Rezervim i kryer:");
        System.out.println("Data e ardhjes: " + arrival);
        System.out.println("Data e largimit: " + departure);
        System.out.println("Mysafirët: " + guests);
        System.out.println("Pagesa: " + payment);

        showAlert("Rezervimi u krye me sukses!");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rezervim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
