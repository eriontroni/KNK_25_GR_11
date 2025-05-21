package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Models.Offer;
import repository.OfferRepository;
import java.io.IOException;
import java.time.LocalDate;

public class OffersController {

    @FXML private TableView<Offer> offersTable;
    @FXML private TableColumn<Offer, String> titleCol;
    @FXML private TableColumn<Offer, String> descriptionCol;
    @FXML private TableColumn<Offer, Double> discountCol;
    @FXML private TableColumn<Offer, String> startDateCol;
    @FXML private TableColumn<Offer, String> endDateCol;
    @FXML private Button btnBackToHome;
    @FXML private Label currentDateLabel;
    @FXML private Label lblTitle;


    @FXML
    public void initialize() {
        // Initialize UI elements
        lblTitle.setText("Special Offers");
        currentDateLabel.setText(LocalDate.now().toString());

        // Back button action
        btnBackToHome.setOnAction(e -> goBackToHome());


        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));


        loadOffers();
    }
    private void loadOffers() {
        OfferRepository offerRepository = new OfferRepository("Offer");
        var offers = offerRepository.getAll();

        if (offers != null) {
            System.out.println("Offers loaded: " + offers.size()); // Debug
            offersTable.getItems().setAll(offers);
        } else {
            System.out.println("Offers list is null.");
        }
    }


    private void goBackToHome() {
        try {
            Parent homeRoot = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
            Stage stage = (Stage) btnBackToHome.getScene().getWindow();
            stage.setScene(new Scene(homeRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}