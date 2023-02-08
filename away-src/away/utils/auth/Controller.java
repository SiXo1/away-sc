package away.utils.auth;

import javafx.scene.control.*;
import away.utils.files.*;
import javafx.stage.*;
import java.net.*;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.io.*;
import away.*;

public class Controller
{
    @FXML
    private Hyperlink loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField licenseField;
    @FXML
    private Button submitButton;
    @FXML
    private Hyperlink registerButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    
    @FXML
    public void register() throws Exception {
        if (this.usernameField.getText().isEmpty() || this.usernameField.getText() == null) {
            Window.showAlert(Alert.AlertType.ERROR, "Please enter username!");
            return;
        }
        if (this.emailField.getText().isEmpty() || this.emailField.getText() == null) {
            Window.showAlert(Alert.AlertType.ERROR, "Please enter email!");
            return;
        }
        if (this.passwordField.getText().isEmpty() || this.passwordField.getText() == null) {
            Window.showAlert(Alert.AlertType.ERROR, "Please enter password!");
            return;
        }
        if (this.licenseField.getText().isEmpty() || this.licenseField.getText() == null) {
            Window.showAlert(Alert.AlertType.ERROR, "Please enter license key!");
            return;
        }
        final String text = this.usernameField.getText();
        final String text2 = this.emailField.getText();
        final String text3 = this.passwordField.getText();
        final String text4 = this.licenseField.getText();
        if (!text.isEmpty() || !text2.isEmpty() || !text3.isEmpty() || !text4.isEmpty()) {
            Request.register(text, text2, text3, text4);
            this.usernameField.setText("");
            this.emailField.setText("");
            this.passwordField.setText("");
            this.licenseField.setText("");
        }
    }
    
    @FXML
    void hideScene() throws IOException {
        final Stage stage = (Stage)this.submitButton.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/assets/fxml/ed108f6919ebadc8e809f8b86ef40b05.fxml")))));
        stage.hide();
    }
    
    @FXML
    void loginScene() throws IOException {
        ((Stage)this.loginButton.getScene().getWindow()).setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/assets/fxml/ed108f6919ebadc8e809f8b86ef40b05.fxml")))));
    }
    
    @FXML
    public void login() throws Exception {
        if (this.usernameField.getText().isEmpty() || this.usernameField.getText() == null) {
            Window.showAlert(Alert.AlertType.ERROR, "Please enter username!");
            return;
        }
        if (this.passwordField.getText().isEmpty() || this.passwordField.getText() == null) {
            Window.showAlert(Alert.AlertType.ERROR, "Please enter password!");
            return;
        }
        final String text = this.usernameField.getText();
        final String text2 = this.passwordField.getText();
        if ((!text.isEmpty() || !text2.isEmpty() || !HWID.getHWID().isEmpty()) && Request.login(text, text2, HWID.getHWID()).contains("XDDDDDDDDDDDD")) {
            this.hideScene();
            Away.start();
        }
    }
    
    @FXML
    void registerScene() throws IOException {
        ((Stage)this.registerButton.getScene().getWindow()).setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/assets/fxml/fe48d9de8ee2c143940417fd4894278a.fxml")))));
    }
}
