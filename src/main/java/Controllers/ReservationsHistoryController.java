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

    @FXML
    private TableView<ReservationHistory> reservationTable;

    @FXML
    private TableColumn<ReservationHistory, Integer> colRoom;

    @FXML
    private TableColumn<ReservationHistory, Integer> colUser;

    @FXML
    private TableColumn<ReservationHistory, String> colStatus;

    @FXML
    private TableColumn<ReservationHistory, Void> colActions;

    @FXML
    private TableColumn<ReservationHistory, String> colDate;

    @FXML
    private TableColumn<ReservationHistory, String> colOld;

    @FXML
    private TableColumn<ReservationHistory, String> colNew;


    @FXML
    public void initialize() {
        ReservationHistoryRepository repo = new ReservationHistoryRepository();
        ObservableList<ReservationHistory> list = FXCollections.observableArrayList(repo.getAll());

        colRoom.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colDate.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getChangeDate().toString())
        );
        colOld.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getOldStatus())
        );
        colNew.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNewStatus())
        );

        reservationTable.setItems(list);
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
