package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML private Button btnHome;
    @FXML private Button btnRooms;
    @FXML private Button btnOffers;
    @FXML private Button btnEvents;
    @FXML private Button btnReservations;
    @FXML private Button accBtn;
    @FXML private Button reviewsBtn;

    // initialize() quhet automatikisht kur ngarkohet FXML
    @FXML
    public void initialize() {
        btnHome.setOnAction(e -> handleHome());
        btnRooms.setOnAction(e -> handleRooms());
        btnOffers.setOnAction(e -> handleOffers());
        btnEvents.setOnAction(e -> handleEvents());
        btnReservations.setOnAction(e -> handleReservations());
        accBtn.setOnAction(e -> handleAccount());
        reviewsBtn.setOnAction(e -> handleReviews());
    }

    public void handleHome() {
        System.out.println("Home clicked");
        // logjika për home
    }

    public void handleRooms() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Room.fxml"));
            Parent offersRoot = loader.load();

            Stage stage = (Stage) btnOffers.getScene().getWindow();
            Scene scene = new Scene(offersRoot);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleOffers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/offers.fxml"));
            Parent offersRoot = loader.load();

            Stage stage = (Stage) btnOffers.getScene().getWindow();
            Scene scene = new Scene(offersRoot);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleEvents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/event.fxml"));
            Parent offersRoot = loader.load();

            Stage stage = (Stage) btnEvents.getScene().getWindow();
            Scene scene = new Scene(offersRoot);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleReservations() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Reservations.fxml"));
            Parent offersRoot = loader.load();

            Stage stage = (Stage) btnReservations.getScene().getWindow();
            Scene scene = new Scene(offersRoot);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAccount() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/firstpage.fxml"));
            Parent offersRoot = loader.load();

            Stage stage = (Stage) accBtn.getScene().getWindow();
            Scene scene = new Scene(offersRoot);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleReviews() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Feedback.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) reviewsBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}