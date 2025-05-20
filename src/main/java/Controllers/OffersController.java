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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private Connection connection;


    @FXML
    public void initialize() {
        // Initialize UI elements
        lblTitle.setText("Special Offers");
        currentDateLabel.setText(LocalDate.now().toString());

        // Back button action
        btnBackToHome.setOnAction(e -> goBackToHome());

        // Setup columns
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        discountCol.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        // Populate the table
        loadOffers();
    }
    private void loadOffers() {
        OfferRepository offerRepository = new OfferRepository("Offer");
        var offers = offerRepository.getAll(); // kjo do kthejë listën e ofertave

        if (offers != null) {
            offersTable.getItems().setAll(offers);
        }
    }
    public List<Offer> getAll() {
        List<Offer> offers = new ArrayList<>();
        String query = "SELECT * FROM Offer";

        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Offer offer = Offer.getInstance(res);;
                if (offer != null) {
                    offers.add(offer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offers;
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