package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

        private void handleHome() {
            System.out.println("Home clicked");
            // logjika për home
        }

        private void handleRooms() {
            System.out.println("Rooms clicked");
            // logjika për rooms
        }

        private void handleOffers() {
            System.out.println("Offers clicked");
            // logjika për offers
        }

        private void handleEvents() {
            System.out.println("Events clicked");
            // logjika për events
        }

        private void handleReservations() {
            System.out.println("Reservations clicked");
            // logjika për reservations
        }
    }

