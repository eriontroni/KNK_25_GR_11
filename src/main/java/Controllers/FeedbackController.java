package Controllers;

import Models.Feedback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import repository.FeedbackRepository;

import java.io.IOException;
import java.util.ArrayList;

public class FeedbackController {

    @FXML
    private VBox testimonialContainer;

    @FXML
    private Button backBtn;  // Shto backBtn këtu

    private FeedbackRepository feedbackRepository;

    @FXML
    public void initialize() {
        feedbackRepository = new FeedbackRepository();
        loadFeedbackComments();

        backBtn.setOnAction(e -> handleBack());
    }

    private void loadFeedbackComments() {
        ArrayList<Feedback> feedbacks = feedbackRepository.getAll();
        testimonialContainer.getChildren().clear();

        for (Feedback fb : feedbacks) {
            Label commentLabel = new Label(fb.getComment());
            commentLabel.setWrapText(true);
            commentLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333;");

            VBox reviewBox = new VBox(commentLabel);
            reviewBox.setPadding(new Insets(15));
            reviewBox.setStyle(
                    "-fx-border-color: #b49a7a; " +
                            "-fx-border-width: 2; " +
                            "-fx-background-color: white; " +
                            "-fx-border-radius: 5; " +
                            "-fx-background-radius: 5;"
            );

            testimonialContainer.getChildren().add(reviewBox);
        }
    }

    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}