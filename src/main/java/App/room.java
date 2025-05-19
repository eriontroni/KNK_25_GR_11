package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class room extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Room.fxml")); // emri i saktë i fajllit
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Room Viewer");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
