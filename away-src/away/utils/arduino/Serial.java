package away.utils.arduino;

import com.fazecast.jSerialComm.*;
import javafx.scene.control.*;
import away.utils.files.*;
import java.security.*;

public class Serial
{
    public static SerialPort arduinoPort;
    public static Timer moveTimer;
    
    public static void connect() throws NoSuchAlgorithmException {
        for (final SerialPort arduinoPort : SerialPort.getCommPorts()) {
            if (arduinoPort.getDescriptivePortName().contains("Serielles USB") || arduinoPort.getDescriptivePortName().contains("Arduino") || arduinoPort.getDescriptivePortName().contains("Serial USB") || arduinoPort.getDescriptivePortName().contains("USB Seri")) {
                Serial.arduinoPort = arduinoPort;
            }
        }
        if (Serial.arduinoPort == null) {
            Window.showAlert(Alert.AlertType.ERROR, "Arduino port is not found.");
            System.exit(1337);
        }
        if (!Serial.arduinoPort.openPort()) {
            Window.showAlert(Alert.AlertType.ERROR, "Arduino port using by another application.");
            System.exit(1337);
        }
    }
    
    public static void mouseMove(final double n, final double n2, final int n3) {
        if (Serial.moveTimer.delay((float)n3)) {
            Serial.arduinoPort.writeBytes(new byte[] { (byte)n, (byte)n2 }, 2L);
            Serial.arduinoPort.flushIOBuffers();
            Serial.moveTimer.reset();
        }
    }
    
    static {
        Serial.moveTimer = new Timer();
    }
}
