package App;

import Utils.SceneLocator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class leon extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Ngarkon FXML nga folderi /resources/views/
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SceneLocator.Reception_Dashboard_Page));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paneli - Recepsionisti");
        stage.setResizable(false); // opsional, qe mos me lëviz dritarja
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
