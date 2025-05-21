package Controllers;

import Models.Notifications;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.NotificationsRepository;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotificationController {
    @FXML
    private VBox notificationListVBox;
    @FXML
    private Button btnBackToHome;

    private NotificationsRepository notificationRepo = new NotificationsRepository();

    @FXML
    public void initialize() {
        // Merr të gjitha njoftimet nga databaza
        List<Notifications> notifications = notificationRepo.getAll();

        if (notifications.isEmpty()) {
            Label noNotif = new Label("Nuk ka njoftime për t'u shfaqur.");
            notificationListVBox.getChildren().add(noNotif);
            return;
        }

        // Shto secilin njoftim në VBox
        for (Notifications notif : notifications) {
            HBox notifBox = new HBox(18);
            notifBox.setStyle("-fx-background-color: #e3f2fd; -fx-background-radius: 10; -fx-padding: 16;");

            // Ikona
            Label iconLabel = new Label("🔔");
            iconLabel.setStyle("-fx-font-size: 24px;");

            // Përmbajtja
            VBox contentBox = new VBox(4);

            Label msgLabel = new Label(notif.getMessage());
            msgLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

            // Formato datën
            String formattedDate = notif.getCreatedAt().toString();
            Label dateLabel = new Label(formattedDate);
            dateLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 12px;");

            contentBox.getChildren().addAll(msgLabel, dateLabel);

            // Hapësirë automatike
            Region spacer = new Region();
            HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

            // Badge statusi
            Label statusLabel = new Label(notif.isRead() ? "Lexuar" : "Pa lexuar");
            statusLabel.setStyle(
                    notif.isRead()
                            ? "-fx-background-color: #55efc4; -fx-background-radius: 8; -fx-text-fill: #262626; -fx-padding: 4 12; -fx-font-size: 12px;"
                            : "-fx-background-color: #ff5252; -fx-background-radius: 8; -fx-text-fill: white; -fx-padding: 4 12; -fx-font-size: 12px;"
            );

            notifBox.getChildren().addAll(iconLabel, contentBox, spacer, statusLabel);

            notificationListVBox.getChildren().add(notifBox);
        }
    }

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
}
