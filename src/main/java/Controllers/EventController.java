package Controllers;

import Models.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import repository.EventRepository;

import java.util.ArrayList;

public class EventController {

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, String> nameCol;

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
    public void initialize() {
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEvent_name()));
        organizerCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getOrganizer_name()));
        dateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEvent_date()));
        timeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEvent_time()));
        roomCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getRoom_id()));
        descCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));

        loadEvents();
    }

    private void loadEvents() {
        ArrayList<Event> events = eventRepo.getAllEvents();
        ObservableList<Event> eventList = FXCollections.observableArrayList(events);
        eventTable.setItems(eventList);
    }
}
