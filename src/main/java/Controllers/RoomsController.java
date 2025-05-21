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

            HBox roomBox = new HBox(20);
            roomBox.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-padding: 15; -fx-background-radius: 10;");

            ImageView imageView = new ImageView();
            if (!images.isEmpty()) {
                imageView.setImage(new Image("file:images/" + images.get(0).getImgURL()));
            }
            imageView.setFitHeight(120);
            imageView.setFitWidth(160);
            imageView.setPreserveRatio(true);

            VBox detailsBox = new VBox(5);
            Label roomNumberLabel = new Label("Room " + room.getRoomNumber());
            roomNumberLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

            Label typeLabel = new Label(type.getName() + " - " + type.getDescription());
            Label capacityLabel = new Label("Capacity: " + type.getCapacity() + " persons");
            Label priceLabel = new Label("Price: $" + String.format("%.2f", type.getPrice_per_night()) + "/night");

            Label availableLabel = new Label("Available: ");
            Label isAvailableLabel = new Label(room.isAvailable() ? "Yes" : "No");
            isAvailableLabel.setStyle("-fx-text-fill: " + (room.isAvailable() ? "green" : "red") + "; -fx-font-weight: bold;");

            HBox availabilityBox = new HBox(10, availableLabel, isAvailableLabel);
            Button bookButton = new Button("Book Now");
            bookButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
            bookButton.setOnAction(e -> handleClick(room.getId()));

            detailsBox.getChildren().addAll(roomNumberLabel, typeLabel, capacityLabel, priceLabel, availabilityBox, bookButton);
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