package away.utils.files;

import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.image.*;
import java.util.*;
import java.security.*;
import java.math.*;

public class Window
{
    public static void showAlert(final Alert.AlertType alertType, final String contentText) throws NoSuchAlgorithmException {
        final Alert alert = new Alert(alertType);
        alert.setTitle(generateTitle());
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/assets/img/icon.png"));
        alert.show();
    }
    
    public static String generateTitle() throws NoSuchAlgorithmException {
        final String string = UUID.randomUUID().toString();
        final MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.reset();
        instance.update(string.getBytes());
        final StringBuilder sb = new StringBuilder(new BigInteger(1, instance.digest()).toString(16));
        while (sb.length() < 32) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }
}
