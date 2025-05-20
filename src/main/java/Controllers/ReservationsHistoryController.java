package Controllers;

import Models.Reservation;
import Models.ReservationHistory;
import Utils.SceneLocator;
import Utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import repository.ReservationHistoryRepository;

import java.util.ArrayList;

public class ReservationsHistoryController {

    public TableColumn colOld;
    public TableColumn colNew;
    @FXML
    private TableView<Reservation> reservationTable;

    @FXML
    private TableColumn<Reservation, Integer> colRoom;

    @FXML
    private TableColumn<Reservation, Integer> colUser;

    @FXML
    private TableColumn<Reservation, String> colStatus;

    @FXML
    private TableColumn<Reservation, Void> colActions;

    @FXML
    private TableColumn<Reservation, String> colDate;

    @FXML
    public void initialize() {
//        colRoom.setCellValueFactory(new PropertyValueFactory<>("roomId"));
//        colUser.setCellValueFactory(new PropertyValueFactory<>("customerId"));
//        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//        colDate.setCellValueFactory(cell -> {
//            String dateRange = cell.getValue().getCheckInDate().toString() + " - " + cell.getValue().getCheckOutDate().toString();
//            return new javafx.beans.property.SimpleStringProperty(dateRange);
//        });
//
//        // Shtimi i butonit Detaje
//        ReservationHistoryRepository rhp = new ReservationHistoryRepository();
//        ArrayList<ReservationHistory> arr = new ArrayList<>(rhp.getAll());
//        // TODO: VESA E NDREQ QETA
//
//        // Test data (pa database për momentin)
//        ObservableList<Reservation> list = FXCollections.observableArrayList(
//
//        );
//
//        reservationTable.setItems(list);
    }

    @FXML
    private void handleAddReservation() {
        System.out.println("Kliko Shto Rezervim");
        // Future: Hapje popup për rezervim të ri
    }

    public void goBack(ActionEvent actionEvent) {
        Stage stage = (Stage) reservationTable.getScene().getWindow();
        SceneManager.switchScene(stage, SceneLocator.Home_Page,"Home Page");
    }
}
