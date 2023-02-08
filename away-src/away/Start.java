package away;

import javafx.application.*;
import javafx.stage.*;
import java.net.*;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.*;
import away.utils.files.*;
import javafx.scene.image.*;
import java.io.*;
import java.security.*;

public class Start extends Application
{
    public static void main(final String[] array) {
        Application.launch(array);
    }
    
    @Override
    public void start(final Stage stage) throws IOException, NoSuchAlgorithmException {
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/assets/fxml/ed108f6919ebadc8e809f8b86ef40b05.fxml")))));
        stage.setTitle(Window.generateTitle());
        stage.getIcons().add(new Image(Objects.requireNonNull(Start.class.getResourceAsStream("/assets/img/icon.png"))));
        stage.setResizable(false);
        stage.show();
    }
}
