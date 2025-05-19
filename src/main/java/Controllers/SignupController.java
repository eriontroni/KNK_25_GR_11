package Controllers;

import Models.DTO.CreateUsersDTO;
import Models.Users;
import Services.PasswordHasher;
import Utils.SceneLocator;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.UsersRepository;
import Services.Validator;

public class SignupController {
    @FXML
    private TextField signupUsername;

    @FXML
    private TextField signupEmail1;
    @FXML
    private TextField signupEmail2;
    @FXML
    private PasswordField signupPass1;
    @FXML
    private PasswordField signupPass2;

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void handleSignup(ActionEvent actionEvent) {
        System.out.println("U kliku");
        String username = signupUsername.getText();
        String email1 = signupEmail1.getText();
        String email2 = signupEmail2.getText();
        String pass1 = signupPass1.getText();
        String pass2 = signupPass2.getText();

        // Email format validation
        if (!Validator.isValidEmail(email1)) {
            showAlert("Formati i email-it është i pavlefshëm!");
            return;
        }
        // Password format validation
        if (!Validator.isValidPassword(pass1)) {
            showAlert("Passwordi duhet të ketë të paktën 8 karaktere, një shkronjë të madhe, një të vogël, një numër dhe një simbol.");
            return;
        }
        // Kontrollo qe email dhe password te jen te njejr
        if (!email1.equals(email2) || !pass1.equals(pass2)) {
            showAlert("Ju lutem rishikoni a keni shënuar njëjtë email ose password!");
            return;
        }
        UsersRepository usersRepository = new UsersRepository();
        if(usersRepository.getByEmail(email1) != null) {
            showAlert("Emaili egziston tashme, zgjedh nje format tjeter!");
        }
        String salt = PasswordHasher.generateSalt();
        String password_hash = PasswordHasher.generateSaltedHash(pass1, salt);
        CreateUsersDTO newUser = new CreateUsersDTO(username, email1, password_hash, salt);
//        UsersRepository usersRepository = new UsersRepository();

        Users user = usersRepository.create(newUser);
        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("User created");
            alert.showAndWait();
        } else {
            showAlert("Gabim gjatë krijimit të userit!");
        }
    }

    public void goBack(ActionEvent actionEvent) {
        Stage stage = (Stage) signupUsername.getScene().getWindow();
        SceneManager.switchScene(stage, SceneLocator.First_Page,"First Page");
    }
}
