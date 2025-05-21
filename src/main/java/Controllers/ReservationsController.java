package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Date;
import java.time.temporal.ChronoUnit;

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
    private ComboBox<String> roomType;

    @FXML
    private TextField reservationCode;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button bookButton;

    @FXML
    public void initialize() {
        arrivalDate.setValue(java.time.LocalDate.now());
        departureDate.setValue(java.time.LocalDate.now().plusDays(1));

        arrivalDate.setOnAction(e -> updatePrice());
        departureDate.setOnAction(e -> updatePrice());
        roomType.setOnAction(e -> updatePrice());
    }

    @FXML
    private void handleBook() {
        Date arrival = arrivalDate.getValue() != null ? Date.valueOf(arrivalDate.getValue()) : null;
        Date departure = departureDate.getValue() != null ? Date.valueOf(departureDate.getValue()) : null;
        String guests = guestCombo.getValue();
        String payment = paymentMethod.getValue();
        String room = roomType.getValue();
        String code = reservationCode.getText();

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

    private void updatePrice() {
        if (arrivalDate.getValue() == null || departureDate.getValue() == null || roomType.getValue() == null)
            return;

        long nights = ChronoUnit.DAYS.between(arrivalDate.getValue(), departureDate.getValue());
        if (nights <= 0) {
            totalPriceLabel.setText("€0.00");
            return;
        }

        double pricePerNight = switch (roomType.getValue()) {
            case "Single" -> 50;
            case "Double" -> 80;
            case "Suite" -> 120;
            default -> 0;
        };

        double total = nights * pricePerNight;
        totalPriceLabel.setText(String.format("€%.2f", total));
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rezervim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
