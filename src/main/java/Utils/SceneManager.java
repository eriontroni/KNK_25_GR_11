package Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    /**  SHEMBULL I PERDORIMIT TE SWITCHSCENE NE KOD:
     * public void goBack(ActionEvent actionEvent) {
     *         Stage stage = (Stage) signupUsername.getScene().getWindow();
     *         SceneManager.switchScene(stage, SceneLocator.First_Page,"First Page");
     *     }
     *     
     */
    public static void switchScene(Stage stage, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Opsionale: Alert me mesazh gabimi
        }
    }
}
