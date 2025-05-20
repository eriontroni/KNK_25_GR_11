package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class vesa extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/offers.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Hotel Offers");
        stage.show();
//        oferrep = new or
//        ofer ofer = ofer.getby title
    }
}
//C:\Users\Erion\Documents\GitHub\KNK_25_GR_11\src\main\resources\images\img.png