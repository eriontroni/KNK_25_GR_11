package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // FXML duhet të jetë në të njëjtin folder me Main ose brenda një resource package, p.sh. /fxml/logini.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/logini.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Log In");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
