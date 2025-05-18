package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomsController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    private Connection connectDB() throws SQLException {
        // Lidhu me databazën
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "");
    }

    // Klasë ndihmëse për përfaqësimin e dhomave
    public static class Room {
        public int id;
        public String number;
        public String type;
        public int capacity;
        public double price;
        public boolean available;
        public String imagePath;

        public Room(int id, String number, String type, int capacity, double price, boolean available, String imagePath) {
            this.id = id;
            this.number = number;
            this.type = type;
            this.capacity = capacity;
            this.price = price;
            this.available = available;
            this.imagePath = imagePath;
        }
    }

    @FXML
    public void initialize() {
        List<Room> rooms = getRoomList();
        int column = 0;
        int row = 0;

        for (Room room : rooms) {
            VBox card = createRoomCard(room);

            gridPane.add(card, column, row);
            column++;

            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    private List<Room> getRoomList() {
        List<Room> list = new ArrayList<>();

        String query = "SELECT r.id, r.number, rt.name AS type, rt.capacity, rt.price, r.available, ri.image_path " +
                "FROM Room r JOIN RoomType rt ON r.room_type_id = rt.id " +
                "LEFT JOIN RoomImage ri ON r.id = ri.room_id";

        try (Connection conn = connectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String type = rs.getString("type");
                int capacity = rs.getInt("capacity");
                double price = rs.getDouble("price");
                boolean available = rs.getBoolean("available");
                String imagePath = rs.getString("image_path");

                Room room = new Room(id, number, type, capacity, price, available, imagePath);
                list.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private VBox createRoomCard(Room room) {
        VBox vbox = new VBox(5);
        vbox.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-padding: 10;");
        vbox.setPrefWidth(220);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        try {
            if (room.imagePath != null) {
                imageView.setImage(new Image(new FileInputStream(room.imagePath)));
            } else {
                imageView.setImage(new Image(getClass().getResourceAsStream("/images/img.png")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Label roomNumber = new Label("Room " + room.number);
        roomNumber.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Label typeLabel = new Label(room.type);
        Label capacityLabel = new Label("Capacity: " + room.capacity + " persons");
        Label priceLabel = new Label("Price: $" + room.price + "/night");

        Label availability = new Label(room.available ? "Yes" : "No");
        availability.setStyle(room.available ? "-fx-text-fill: green; -fx-font-weight: bold;" : "-fx-text-fill: red; -fx-font-weight: bold;");

        HBox availabilityBox = new HBox(10, new Label("Available:"), availability);
        availabilityBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        Button bookButton = new Button("Book Now");
        bookButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        bookButton.setOnAction(e -> {
            System.out.println("Booking room " + room.number + "...");
            // Mund të hapësh një dritare për rezervim këtu
        });

        vbox.getChildren().addAll(imageView, roomNumber, typeLabel, capacityLabel, priceLabel, availabilityBox, bookButton);
        return vbox;
    }
}
