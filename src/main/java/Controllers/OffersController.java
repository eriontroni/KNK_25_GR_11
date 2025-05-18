package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OffersController {

    @FXML
    private Button btnBackToHome;

    @FXML
    private VBox offersContainer;

    @FXML
    private Label lblTitle;

    @FXML
    public void initialize() {
        // Set up basic UI
        lblTitle.setText("Special Offers");

        // Back button action
        btnBackToHome.setOnAction(e -> goBackToHome());

        // Load offers
        loadOffers();
    }

    private void loadOffers() {
        // Clear existing offers
        offersContainer.getChildren().clear();

        // Add sample offers (replace with your actual offers)
        addOffer("Summer Special", "20% OFF", "Valid until August 31, 2023");
        addOffer("Weekend Getaway", "15% OFF", "Friday-Sunday bookings only");
        addOffer("Early Bird", "10% OFF", "Book 30 days in advance");
    }

    private void addOffer(String title, String discount, String details) {
        // Create offer UI elements
        Label offerTitle = new Label(title);
        offerTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label offerDiscount = new Label(discount);
        offerDiscount.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px;");

        Label offerDetails = new Label(details);
        offerDetails.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        // Add to container
        offersContainer.getChildren().addAll(offerTitle, offerDiscount, offerDetails);

        // Add some spacing
        offersContainer.getChildren().add(new Label(" "));
    }

    private void goBackToHome() {
        System.out.println("Going back to home");
        // Add your navigation logic here
    }
}