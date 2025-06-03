package Controllers;

import Services.ReservationService;
import Utils.SceneLocator;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import Models.DTO.CreateReservationDTO;
import Models.Reservation;
import javafx.stage.Stage;
import repository.ReservationRepository;
import repository.RoomRepository;
import Models.Room;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ReservationsReceptionController {
    @FXML private DatePicker arrivalDate;
    @FXML private DatePicker departureDate;
    @FXML private ComboBox<String> paymentMethod;
    @FXML private ComboBox<String> roomType;
    @FXML private TextField reservationCode;
    @FXML private Label totalPriceLabel;
    @FXML private Button bookButton;

    // Qmimet per dhomat, mund te lexohen nga DB nese do automatizim
    private Map<String, Double> roomTypePrices = new HashMap<>();
    private Map<String, Integer> roomTypeToRoomId = new HashMap<>();

    private ReservationRepository reservationRepo = new ReservationRepository();
    private RoomRepository roomRepo = new RoomRepository(); // Supozojmë se e ke këtë repository


    @FXML
    public void initialize() {
        // Kjo është për test. Nëse i ke dhomat në DB, merr të gjitha me roomRepo.getAll();
        List<Room> allRooms = roomRepo.getAll();
        for (Room room : allRooms) {
            // Nëse ke më shumë se një dhomë për tip, vendos logjikë tjetër për zgjedhjen
            if (!roomTypeToRoomId.containsKey(ReservationService.getTypeFromRoom(room))) {
                roomTypeToRoomId.put(ReservationService.getTypeFromRoom(room), room.getId());
                // Shëno një qmim për çdo tip dhome, ose merr nga DB nëse është në model
                roomTypePrices.put(ReservationService.getTypeFromRoom(room), ReservationService.getPriceFromRoom(room));
            }
        }

        // Listeners për update të çmimit
        roomType.valueProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
        arrivalDate.valueProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
        departureDate.valueProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
    }

    private void updateTotalPrice() {
        String type = roomType.getValue();
        LocalDate arrival = arrivalDate.getValue();
        LocalDate departure = departureDate.getValue();
        if (type == null || arrival == null || departure == null || !departure.isAfter(arrival)) {
            totalPriceLabel.setText("€0.00");
            return;
        }

        long nights = ChronoUnit.DAYS.between(arrival, departure);
        Double pricePerNight = roomTypePrices.get(type);
        if (pricePerNight == null) {
            totalPriceLabel.setText("€0.00");
            return;
        }
        double total = pricePerNight * nights;

        // Shembull: zbritje nese kodi eshte "DISCOUNT10"
        if (!reservationCode.getText().isEmpty() && reservationCode.getText().equalsIgnoreCase("DISCOUNT10")) {
            total = total * 0.9;
        }

        totalPriceLabel.setText("€" + String.format("%.2f", total));
    }

    @FXML
    private void handleBook() {
        try {
            String type = roomType.getValue();
            String payment = paymentMethod.getValue();
            LocalDate arrival = arrivalDate.getValue();
            LocalDate departure = departureDate.getValue();

            if (type == null || payment == null || arrival == null || departure == null) {
                showAlert(Alert.AlertType.WARNING, "Ploteso te gjitha fushat kryesore!");
                return;
            }
            if (!departure.isAfter(arrival)) {
                showAlert(Alert.AlertType.WARNING, "Data e largimit duhet te jete pas asaj te arritjes!");
                return;
            }

            // Shtyp totalin
            double total = Double.parseDouble(totalPriceLabel.getText().replace("€", ""));

            // Gjej roomId sipas tipit (nëse ke zgjedhje të dhomave sipas llojit – ndryshe duhet ta ndryshosh këtë)
            Integer roomId = roomTypeToRoomId.get(type);
            if (roomId == null) {
                showAlert(Alert.AlertType.ERROR, "S'ka dhome të lirë për këtë tip!");
                return;
            }

            // Vendos këtu customerId (p.sh. nga session ose useri i kyçur)
            int customerId = getLoggedInCustomerId();

            // Oferta nuk është e domosdoshme, vendose null
            Integer offerId = 1;

            CreateReservationDTO dto = new CreateReservationDTO(
                    0, // id, për create nuk duhet
                    customerId,
                    roomId,
                    offerId,
                    Date.valueOf(arrival),
                    Date.valueOf(departure),
                    "Pending", // status default
                    total
            );
            ReservationRepository rr = new ReservationRepository();
            Reservation reservation = rr.create(dto);
            if (reservation != null) {
                showAlert(Alert.AlertType.INFORMATION, "Rezervimi u shtua me sukses!");
                clearForm();
            } else {
                showAlert(Alert.AlertType.ERROR, "Deshtoi ruajtja e rezervimit!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Gabim: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Rezervimet");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        arrivalDate.setValue(null);
        departureDate.setValue(null);
        paymentMethod.setValue(null);
        roomType.setValue(null);
        reservationCode.clear();
        totalPriceLabel.setText("€0.00");
    }

    private int getLoggedInCustomerId() {
        // TODO: nxirre nga session, ose hardcode për testim p.sh.
        return 1;
    }

    public void handleBallina(ActionEvent actionEvent) {
        Stage stage = (Stage) bookButton.getScene().getWindow();
        SceneManager.switchScene(stage, SceneLocator.Reception_Dashboard_Page,"Recepcioni");
    }
}
