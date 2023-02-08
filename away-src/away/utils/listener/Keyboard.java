package away.utils.listener;

import org.jnativehook.keyboard.*;
import away.*;
import away.utils.files.*;
import javax.sound.sampled.*;
import java.io.*;

public class Keyboard implements NativeKeyListener
{
    public void nativeKeyTyped(final NativeKeyEvent nativeKeyEvent) {
        if (!Away.trigger_keybind.equals("LMB") && !Away.trigger_keybind.equals("RMB") && !Away.trigger_keybind.equals("MMB") && !Away.trigger_keybind.equals("MB4") && !Away.trigger_keybind.equals("MB5") && String.valueOf(nativeKeyEvent.getKeyChar()).toUpperCase().equals(Away.trigger_keybind)) {
            Away.trigger_enabled = !Away.trigger_enabled;
            if (Away.trigger_enabled) {
                try {
                    Sound.playSound("/assets/sound/notification.wav");
                }
                catch (LineUnavailableException ex) {}
                catch (UnsupportedAudioFileException ex2) {}
                catch (IOException ex3) {}
            }
        }
        if (!Away.rcs_keybind.equals("LMB") && !Away.rcs_keybind.equals("RMB") && !Away.rcs_keybind.equals("MMB") && !Away.rcs_keybind.equals("MB4") && !Away.rcs_keybind.equals("MB5") && String.valueOf(nativeKeyEvent.getKeyChar()).toUpperCase().equals(Away.rcs_keybind)) {
            Away.rcs_activated = !Away.rcs_activated;
            if (Away.rcs_activated) {
                final String s = "/assets/sound/notification.wav";
                try {
                    Sound.playSound(s);
                }
                catch (LineUnavailableException ex4) {}
                catch (UnsupportedAudioFileException ex5) {}
                catch (IOException ex6) {}
            }
        }
        if (!Away.keybind.equals("LMB") && !Away.keybind.equals("RMB") && !Away.keybind.equals("MMB") && !Away.keybind.equals("MB4") && !Away.keybind.equals("MB5") && Away.mode.equals("TOGGLE") && String.valueOf(nativeKeyEvent.getKeyChar()).toUpperCase().equals(Away.keybind)) {
            Away.enabled = !Away.enabled;
            if (Away.enabled) {
                final String s2 = "/assets/sound/notification.wav";
                try {
                    Sound.playSound(s2);
                }
                catch (LineUnavailableException ex7) {}
                catch (UnsupportedAudioFileException ex8) {}
                catch (IOException ex9) {}
            }
        }
    }
    
    public void nativeKeyPressed(final NativeKeyEvent nativeKeyEvent) {
        if (!Away.trigger_keybind.equals("LMB") && !Away.trigger_keybind.equals("RMB") && !Away.trigger_keybind.equals("MMB") && !Away.trigger_keybind.equals("MB4") && !Away.trigger_keybind.equals("MB5") && Away.trigger_mode.equals("HOLD") && NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equals(Away.trigger_keybind)) {
            Away.trigger_enabled = true;
        }
        if (!Away.keybind.equals("LMB") && !Away.keybind.equals("RMB") && !Away.keybind.equals("MMB") && !Away.keybind.equals("MB4") && !Away.keybind.equals("MB5") && Away.mode.equals("HOLD") && NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equals(Away.keybind)) {
            Away.enabled = true;
        }
    }
    
    public void nativeKeyReleased(final NativeKeyEvent nativeKeyEvent) {
        if (!Away.trigger_keybind.equals("LMB") && !Away.trigger_keybind.equals("RMB") && !Away.trigger_keybind.equals("MMB") && !Away.trigger_keybind.equals("MB4") && !Away.trigger_keybind.equals("MB5") && Away.trigger_mode.equals("HOLD") && NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equals(Away.trigger_keybind)) {
            Away.trigger_enabled = false;
        }
        if (!Away.keybind.equals("LMB") && !Away.keybind.equals("RMB") && !Away.keybind.equals("MMB") && !Away.keybind.equals("MB4") && !Away.keybind.equals("MB5") && Away.mode.equals("HOLD") && NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).equals(Away.keybind)) {
            Away.enabled = false;
        }
    }
}
