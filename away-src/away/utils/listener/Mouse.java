package away.utils.listener;

import org.jnativehook.mouse.*;
import away.*;
import away.utils.files.*;
import javax.sound.sampled.*;
import java.io.*;

public class Mouse implements NativeMouseListener
{
    public static long millis;
    
    public void nativeMouseClicked(final NativeMouseEvent nativeMouseEvent) {
    }
    
    public void nativeMouseReleased(final NativeMouseEvent nativeMouseEvent) {
        if (Away.rcs_activated && nativeMouseEvent.getButton() == 1) {
            Away.rcs_enabled = false;
            Mouse.millis = 0L;
        }
        if ((Away.keybind.equals("LMB") || Away.keybind.equals("RMB") || Away.keybind.equals("MMB") || Away.keybind.equals("MB4") || Away.keybind.equals("MB5")) && Away.mode.equals("HOLD")) {
            if (Away.keybind.equals("LMB") && nativeMouseEvent.getButton() == 1) {
                Away.enabled = false;
            }
            if (Away.keybind.equals("RMB") && nativeMouseEvent.getButton() == 2) {
                Away.enabled = false;
            }
            if (Away.keybind.equals("MMB") && nativeMouseEvent.getButton() == 3) {
                Away.enabled = false;
            }
            if (Away.keybind.equals("MB4") && nativeMouseEvent.getButton() == 4) {
                Away.enabled = false;
            }
            if (Away.keybind.equals("MB5") && nativeMouseEvent.getButton() == 5) {
                Away.enabled = false;
            }
        }
        if ((Away.trigger_keybind.equals("LMB") || Away.trigger_keybind.equals("RMB") || Away.trigger_keybind.equals("MMB") || Away.trigger_keybind.equals("MB4") || Away.trigger_keybind.equals("MB5")) && Away.trigger_mode.equals("HOLD")) {
            if (Away.trigger_keybind.equals("RMB") && nativeMouseEvent.getButton() == 2) {
                Away.trigger_enabled = false;
            }
            if (Away.trigger_keybind.equals("MMB") && nativeMouseEvent.getButton() == 3) {
                Away.trigger_enabled = false;
            }
            if (Away.trigger_keybind.equals("MB4") && nativeMouseEvent.getButton() == 4) {
                Away.trigger_enabled = false;
            }
            if (Away.trigger_keybind.equals("MB5") && nativeMouseEvent.getButton() == 5) {
                Away.trigger_enabled = false;
            }
        }
    }
    
    public void nativeMousePressed(final NativeMouseEvent nativeMouseEvent) {
        if (Away.rcs_activated && nativeMouseEvent.getButton() == 1) {
            Away.rcs_enabled = true;
            Mouse.millis = System.currentTimeMillis();
        }
        if (Away.keybind.equals("LMB") || Away.keybind.equals("RMB") || Away.keybind.equals("MMB") || Away.keybind.equals("MB4") || Away.keybind.equals("MB5")) {
            if (Away.mode.equals("HOLD")) {
                if (Away.keybind.equals("LMB") && nativeMouseEvent.getButton() == 1) {
                    Away.enabled = true;
                }
                if (Away.keybind.equals("RMB") && nativeMouseEvent.getButton() == 2) {
                    Away.enabled = true;
                }
                if (Away.keybind.equals("MMB") && nativeMouseEvent.getButton() == 3) {
                    Away.enabled = true;
                }
                if (Away.keybind.equals("MB4") && nativeMouseEvent.getButton() == 4) {
                    Away.enabled = true;
                }
                if (Away.keybind.equals("MB5") && nativeMouseEvent.getButton() == 5) {
                    Away.enabled = true;
                }
            }
            if (Away.mode.equals("TOGGLE")) {
                if (Away.keybind.equals("LMB") && nativeMouseEvent.getButton() == 1) {
                    Away.enabled = !Away.enabled;
                    if (Away.enabled) {
                        final String s = "/assets/sound/notification.wav";
                        try {
                            Sound.playSound(s);
                        }
                        catch (LineUnavailableException ex) {}
                        catch (UnsupportedAudioFileException ex2) {}
                        catch (IOException ex3) {}
                    }
                }
                if (Away.keybind.equals("RMB") && nativeMouseEvent.getButton() == 2) {
                    Away.enabled = !Away.enabled;
                    if (Away.enabled) {
                        final String s2 = "/assets/sound/notification.wav";
                        try {
                            Sound.playSound(s2);
                        }
                        catch (LineUnavailableException ex4) {}
                        catch (UnsupportedAudioFileException ex5) {}
                        catch (IOException ex6) {}
                    }
                }
                if (Away.keybind.equals("MMB") && nativeMouseEvent.getButton() == 3) {
                    Away.enabled = !Away.enabled;
                    if (Away.enabled) {
                        try {
                            Sound.playSound("/assets/sound/notification.wav");
                        }
                        catch (LineUnavailableException ex7) {}
                        catch (UnsupportedAudioFileException ex8) {}
                        catch (IOException ex9) {}
                    }
                }
                if (Away.keybind.equals("MB4") && nativeMouseEvent.getButton() == 4) {
                    Away.enabled = !Away.enabled;
                    if (Away.enabled) {
                        final String s3 = "/assets/sound/notification.wav";
                        try {
                            Sound.playSound(s3);
                        }
                        catch (LineUnavailableException ex10) {}
                        catch (UnsupportedAudioFileException ex11) {}
                        catch (IOException ex12) {}
                    }
                }
                if (Away.keybind.equals("MB5") && nativeMouseEvent.getButton() == 5) {
                    Away.enabled = !Away.enabled;
                    if (Away.enabled) {
                        try {
                            Sound.playSound("/assets/sound/notification.wav");
                        }
                        catch (LineUnavailableException ex13) {}
                        catch (UnsupportedAudioFileException ex14) {}
                        catch (IOException ex15) {}
                    }
                }
            }
        }
        if (Away.trigger_keybind.equals("LMB") || Away.trigger_keybind.equals("RMB") || Away.trigger_keybind.equals("MMB") || Away.trigger_keybind.equals("MB4") || Away.trigger_keybind.equals("MB5")) {
            if (Away.trigger_mode.equals("HOLD")) {
                if (Away.trigger_keybind.equals("RMB") && nativeMouseEvent.getButton() == 2) {
                    Away.trigger_enabled = true;
                }
                if (Away.trigger_keybind.equals("MMB") && nativeMouseEvent.getButton() == 3) {
                    Away.trigger_enabled = true;
                }
                if (Away.trigger_keybind.equals("MB4") && nativeMouseEvent.getButton() == 4) {
                    Away.trigger_enabled = true;
                }
                if (Away.trigger_keybind.equals("MB5") && nativeMouseEvent.getButton() == 5) {
                    Away.trigger_enabled = true;
                }
            }
            if (Away.trigger_mode.equals("TOGGLE")) {
                if (Away.trigger_keybind.equals("RMB") && nativeMouseEvent.getButton() == 2) {
                    Away.trigger_enabled = !Away.trigger_enabled;
                    if (Away.trigger_enabled) {
                        try {
                            Sound.playSound("/assets/sound/notification.wav");
                        }
                        catch (LineUnavailableException ex16) {}
                        catch (UnsupportedAudioFileException ex17) {}
                        catch (IOException ex18) {}
                    }
                }
                if (Away.trigger_keybind.equals("MMB") && nativeMouseEvent.getButton() == 3) {
                    Away.trigger_enabled = !Away.trigger_enabled;
                    if (Away.trigger_enabled) {
                        try {
                            Sound.playSound("/assets/sound/notification.wav");
                        }
                        catch (LineUnavailableException ex19) {}
                        catch (UnsupportedAudioFileException ex20) {}
                        catch (IOException ex21) {}
                    }
                }
                if (Away.trigger_keybind.equals("MB4") && nativeMouseEvent.getButton() == 4) {
                    Away.trigger_enabled = !Away.trigger_enabled;
                    if (Away.trigger_enabled) {
                        try {
                            Sound.playSound("/assets/sound/notification.wav");
                        }
                        catch (LineUnavailableException ex22) {}
                        catch (UnsupportedAudioFileException ex23) {}
                        catch (IOException ex24) {}
                    }
                }
                if (Away.trigger_keybind.equals("MB5") && nativeMouseEvent.getButton() == 5) {
                    Away.trigger_enabled = !Away.trigger_enabled;
                    if (Away.trigger_enabled) {
                        final String s4 = "/assets/sound/notification.wav";
                        try {
                            Sound.playSound(s4);
                        }
                        catch (LineUnavailableException ex25) {}
                        catch (UnsupportedAudioFileException ex26) {}
                        catch (IOException ex27) {}
                    }
                }
            }
        }
    }
}
