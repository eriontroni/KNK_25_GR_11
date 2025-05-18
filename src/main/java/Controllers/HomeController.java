package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnRooms;

    @FXML
    private Button btnOffers;

    @FXML
    private Button btnEvents;

    @FXML
    private Button btnReservations;

    // initialize() quhet automatikisht kur ngarkohet FXML
    @FXML
        public void initialize() {
            btnHome.setOnAction(e -> handleHome());
            btnRooms.setOnAction(e -> handleRooms());
            btnOffers.setOnAction(e -> handleOffers());
            btnEvents.setOnAction(e -> handleEvents());
            btnReservations.setOnAction(e -> handleReservations());
        }

    public void handleHome() {
            System.out.println("Home clicked");
            // logjika për home
        }

    public void handleRooms() {
            System.out.println("Rooms clicked");
            // logjika për rooms
        }

    public void handleOffers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/offers.fxml"));
            Parent offersRoot = loader.load();

            // Merr skenën aktuale nga butoni (ose çdo Node tjetër në skenë)
            Stage stage = (Stage) btnOffers.getScene().getWindow();
            Scene scene = new Scene(offersRoot);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    public void handleEvents() {

        }

    public void handleReservations() {
            System.out.println("Reservations clicked");
            // logjika për reservations
        }
    }

