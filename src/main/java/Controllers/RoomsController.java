package Controllers;

import Models.Room;
import Models.RoomImage;
import Models.RoomType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.RoomRepository;

import java.io.IOException;
import java.util.List;

public class RoomsController {
    @FXML
    private VBox roomListVBox;

    private RoomRepository roomRepo = new RoomRepository();

    @FXML
    public void initialize() {
        List<Room> rooms = roomRepo.getAll();
        System.out.println("Numri i dhomave: " + rooms.size()); // Kontrollo sa dhoma ka

        if (rooms.isEmpty()) {
            Label noRoomsLabel = new Label("Nuk ka dhoma të disponueshme ose problem me databazën.");
            roomListVBox.getChildren().add(noRoomsLabel);
            return;
        }

        // Ktu vazhdon kodi per shtimin e dhomave ne VBox
        for (Room room : rooms) {
            RoomType type = roomRepo.getRoomTypeForRoom(room.getId());
            List<RoomImage> images = roomRepo.getRoomImages(room.getId());
            String imgUrl = images.get(0).getImgURL();
            HBox roomBox = new HBox(30);
            roomBox.setStyle("""
    -fx-background-color: #fdfaf6;
    -fx-border-color: #dcd3c3;
    -fx-border-radius: 14;
    -fx-background-radius: 14;
    -fx-padding: 20;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 10, 0, 0, 6);
""");

            ImageView imageView = new ImageView();
            if (!images.isEmpty()) {
                imageView.setImage(new Image(getClass().getResource(imgUrl).toExternalForm()));
            }
            imageView.setFitHeight(130);
            imageView.setFitWidth(180);
            imageView.setPreserveRatio(true);
            imageView.setStyle("-fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 4, 0, 0, 2);");

            VBox detailsBox = new VBox(8);
            detailsBox.setStyle("-fx-padding: 10;");

            Label roomNumberLabel = new Label("Room " + room.getRoomNumber());
            roomNumberLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #5a4a3c;");

            Label typeLabel = new Label(type.getName() + " — " + type.getDescription());
            typeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #6b5746;");

            Label capacityLabel = new Label("Capacity: " + type.getCapacity() + " persons");
            capacityLabel.setStyle("-fx-text-fill: #7e6a58;");

            Label priceLabel = new Label("$" + String.format("%.2f", type.getPrice_per_night()) + " / night");
            priceLabel.setStyle("-fx-text-fill: #7e6a58;");

            Label availableLabel = new Label("Availability: ");
            availableLabel.setStyle("-fx-text-fill: #6b5746;");

            Label isAvailableLabel = new Label(room.isAvailable() ? "Available" : "Not Available");
            isAvailableLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: " + (room.isAvailable() ? "#2ecc71" : "#e74c3c") + ";");

            HBox availabilityBox = new HBox(10, availableLabel, isAvailableLabel);

            Button bookButton = new Button("Book Now");
            bookButton.setStyle("""
    -fx-background-color: linear-gradient(to right, #a1866f, #bca48b);
    -fx-text-fill: white;
    -fx-font-weight: bold;
    -fx-background-radius: 12;
    -fx-padding: 6 16;
    -fx-cursor: hand;
""");
            bookButton.setOnAction(e -> handleClick(room.getId()));

            detailsBox.getChildren().addAll(
                    roomNumberLabel, typeLabel, capacityLabel, priceLabel, availabilityBox, bookButton
            );

            roomBox.getChildren().addAll(imageView, detailsBox);
            roomListVBox.getChildren().add(roomBox);

        }
    }
    @FXML private Button btnBackToHome;
    @FXML
    private void goBackToHome() {
        try {
            Parent homeRoot = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
            Stage stage = (Stage) btnBackToHome.getScene().getWindow();
            stage.setScene(new Scene(homeRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleClick(int roomId) {
        System.out.println("Room " + roomId + " selected for booking.");
        // mduhet me permirsu ktu
    }
}