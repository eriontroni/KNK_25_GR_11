package Controllers;

import Models.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.EventRepository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventController {

    @FXML private TableView<Event> eventTable;

    @FXML private TableColumn<Event, String> nameCol;

    @FXML
    private TableColumn<Event, String> organizerCol;

    @FXML
    private TableColumn<Event, java.util.Date> dateCol;

    @FXML
    private TableColumn<Event, java.time.LocalTime> timeCol;

    @FXML
    private TableColumn<Event, Integer> roomCol;

    @FXML
    private TableColumn<Event, String> descCol;

    private EventRepository eventRepo = new EventRepository();

    @FXML
    private Button btnHome;

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEvent_name()));
        organizerCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getOrganizer_name()));
        dateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEvent_date()));
        timeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEvent_time()));
        roomCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getRoom_id()));
        descCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));
        loadEvents();

        btnHome.setOnAction(e -> goBackToHome());
    }


    private void loadEvents() {
        ArrayList<Event> events = eventRepo.getAllEvents();
        if (events != null) {
            System.out.println("Events loaded: " + events.size()); // Debug
            ObservableList<Event> eventList = FXCollections.observableArrayList(events);
            eventTable.setItems(eventList);
        } else {
            System.out.println("Events list is null.");
        }
    }

    private void goBackToHome() {
        try {
            Parent homeRoot = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
            Stage stage = (Stage) btnHome.getScene().getWindow();
            stage.setScene(new Scene(homeRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
