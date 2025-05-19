package Controllers;

import Models.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ReservationsController {

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
        colRoom.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(cell -> {
            String dateRange = cell.getValue().getCheckInDate().toString() + " - " + cell.getValue().getCheckOutDate().toString();
            return new javafx.beans.property.SimpleStringProperty(dateRange);
        });

        // Shtimi i butonit Detaje
        Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>> cellFactory = col -> new TableCell<>() {
            private final Button btn = new Button("Detaje");

            {
                btn.setOnAction(e -> {
                    Reservation reservation = getTableView().getItems().get(getIndex());
                    System.out.println("Rezervim: ID = " + reservation.getId() + ", Room = " + reservation.getRoomId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(btn);
                    box.setSpacing(5);
                    setGraphic(box);
                }
            }
        };

        colActions.setCellFactory(cellFactory);

        // Test data (pa database për momentin)
        ObservableList<Reservation> list = FXCollections.observableArrayList(
                new Reservation(1, 101, 2, java.sql.Date.valueOf("2025-05-20"), java.sql.Date.valueOf("2025-05-23"), "Aktive", 240.0),
                new Reservation(2, 102, 3, java.sql.Date.valueOf("2025-05-25"), java.sql.Date.valueOf("2025-05-28"), "Anuluar", 300.0)
        );

        reservationTable.setItems(list);
    }

    @FXML
    private void handleAddReservation() {
        System.out.println("Kliko Shto Rezervim");
        // Future: Hapje popup për rezervim të ri
    }
}
