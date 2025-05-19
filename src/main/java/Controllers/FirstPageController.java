package Controllers;

import Utils.SceneLocator;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FirstPageController {
    public Button butoni;
    public Pane pane;
    public void signinHandle(ActionEvent actionEvent) {
        Stage stage = (Stage) butoni.getScene().getWindow();
        SceneManager.switchScene(stage, SceneLocator.Login_Page,"Login");
    }

    public void signupHandle(ActionEvent actionEvent) {
        Stage stage = (Stage) butoni.getScene().getWindow();
        SceneManager.switchScene(stage, SceneLocator.Signup_Page,"Signup");
    }
}
