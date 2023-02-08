package away;

import java.nio.charset.*;
import org.jnativehook.*;
import java.util.logging.*;
import org.jnativehook.mouse.*;
import away.utils.listener.*;
import org.jnativehook.keyboard.*;
import java.util.function.*;
import away.utils.arduino.*;
import java.awt.*;
import java.awt.image.*;
import com.google.gson.*;
import mslinks.*;
import away.utils.files.*;
import java.util.*;
import java.nio.file.*;
import away.utils.arduino.utils.*;

public class Away
{
    public static int trigger_delay;
    public static boolean enabled;
    public static String rcs_keybind;
    public static int max_value;
    public static double pixel_improve;
    public static int fov_y;
    public static boolean rcs_activated;
    public static String VID;
    public static boolean rcs_enabled;
    public static String color;
    public static String mode;
    public static Timer triggerDelay;
    public static int aim_smooth;
    public static String keybind;
    public static String trigger_keybind;
    public static boolean trigger_enabled;
    public static int pattern;
    public static String PID;
    public static double speed;
    public static int fov_x;
    public static String trigger_mode;
    
    public static void refreshConfig() throws Exception {
        final List<String> allLines = Files.readAllLines(Paths.get(System.getProperty("user.home") + "\\" + File.shuffleFileName(Away.VID) + "\\" + File.shuffleFileName(Away.PID) + ".json", new String[0]), StandardCharsets.UTF_8);
        Away.speed = Double.parseDouble(allLines.get(4).split("\"speed\": ")[1].split(",")[0]);
        Away.fov_x = Integer.parseInt(allLines.get(5).split("\"fov_x\": ")[1].split(",")[0]);
        Away.fov_y = Integer.parseInt(allLines.get(6).split("\"fov_y\": ")[1].split(",")[0]);
        Away.mode = allLines.get(7).split("\"mode\": ")[1].split("\"")[1].split("\"")[0].toUpperCase();
        Away.keybind = allLines.get(8).split("\"bind\": ")[1].split("\"")[1].split("\"")[0].toUpperCase();
        Away.trigger_delay = Integer.parseInt(allLines.get(11).split("\"delay\": ")[1].split(",")[0]);
        Away.trigger_mode = allLines.get(12).split("\"mode\": ")[1].split("\"")[1].split("\"")[0].toUpperCase();
        Away.trigger_keybind = allLines.get(13).split("\"bind\": ")[1].split("\"")[1].split("\"")[0].toUpperCase();
        Away.rcs_keybind = allLines.get(16).split("\"keybind\": ")[1].split("\"")[1].split("\"")[0].toUpperCase();
        Away.pattern = Integer.parseInt(allLines.get(17).split("\"pattern\": ")[1].split(",")[0]);
        Away.max_value = Integer.parseInt(allLines.get(18).split("\"max_value\": ")[1].split(",")[0]);
        Away.aim_smooth = Integer.parseInt(allLines.get(21).split("\"smooth\": ")[1].split(",")[0]);
        Away.color = allLines.get(22).split("\"color\": ")[1].split("\"")[1].split("\"")[0].toUpperCase();
        Away.pixel_improve = Double.parseDouble(allLines.get(23).split("\"pixel_improve\": ")[1].split(",")[0]);
    }
    
    public static void registerNativeHook() throws Exception {
        LogManager.getLogManager().reset();
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeMouseListener((NativeMouseListener)new Mouse());
        GlobalScreen.addNativeKeyListener((NativeKeyListener)new Keyboard());
    }
    
    private static double lambda$null$2(final Vector2D vector2D) {
        return vector2D.y;
    }
    
    private static void lambda$start$1() {
        while (true) {
            refreshConfig();
            final Robot robot = new Robot();
            final GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            final BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(defaultScreenDevice.getDisplayMode().getWidth() / 2 - Away.fov_x, defaultScreenDevice.getDisplayMode().getHeight() / 2 - Away.fov_y, Away.fov_x * 2, Away.fov_y * 2));
            final ArrayList<Vector2D> list = new ArrayList<Vector2D>();
            if (Away.color.contains("PURPLE")) {
                for (int i = 0; i < screenCapture.getWidth(); ++i) {
                    for (int j = 0; j < screenCapture.getHeight(); ++j) {
                        final Color color = new Color(screenCapture.getRGB(i, j));
                        if (Colors.purple(color.getRed(), color.getGreen(), color.getBlue())) {
                            list.add(new Vector2D(i - Away.fov_x, j - Away.fov_y));
                        }
                    }
                }
            }
            else if (Away.color.contains("YELLOW")) {
                for (int k = 0; k < screenCapture.getWidth(); ++k) {
                    for (int l = 0; l < screenCapture.getHeight(); ++l) {
                        final Color color2 = new Color(screenCapture.getRGB(k, l));
                        if (Colors.yellow(color2.getRed(), color2.getGreen(), color2.getBlue())) {
                            list.add(new Vector2D(k - Away.fov_x, l - Away.fov_y));
                        }
                    }
                }
            }
            else if (Away.color.contains("RED")) {
                for (int n = 0; n < screenCapture.getWidth(); ++n) {
                    for (int n2 = 0; n2 < screenCapture.getHeight(); ++n2) {
                        final Color color3 = new Color(screenCapture.getRGB(n, n2));
                        if (Colors.red(color3.getRed(), color3.getGreen(), color3.getBlue())) {
                            list.add(new Vector2D(n - Away.fov_x, n2 - Away.fov_y));
                        }
                    }
                }
            }
            Label_0783: {
                if (!Away.enabled || list.size() <= 0) {
                    break Label_0783;
                }
                list.sort(Comparator.comparingDouble((ToDoubleFunction<? super Vector2D>)Away::lambda$null$0));
                final double x = list.get(0).x;
                final double y = list.get(0).y;
                final double n3 = x * Away.speed;
                final double n4 = y * Away.speed;
                double n5 = Math.abs(list.size() / 5);
                if (n5 < 12.0) {
                    n5 /= 2.0;
                }
                if (Math.round((float)list.size()) <= 5) {
                    n5 = 0.0;
                }
                if (Math.round((float)list.size()) <= 10) {
                    n5 = -0.2;
                }
                if (Math.round((float)list.size()) <= 15) {
                    n5 = 0.7;
                }
                if (Math.round((float)list.size()) >= 12) {
                    n5 = 0.7;
                }
                if (Math.round((float)list.size()) >= 23) {
                    n5 = 1.0;
                }
                if (Math.round((float)list.size()) >= 35) {
                    n5 = -2.0;
                }
                if (Math.round((float)list.size()) >= 85) {
                    n5 = -2.0;
                }
                if (Math.round((float)list.size()) >= 150) {
                    n5 = 1.3;
                }
                final double n6 = n4 + (n5 + Away.pixel_improve);
                if (Away.rcs_enabled && Away.rcs_activated) {
                    int max_value = (int)((System.currentTimeMillis() - Mouse.millis) / Away.pattern);
                    if (max_value >= Away.max_value) {
                        max_value = Away.max_value;
                    }
                    if (max_value < 0) {
                        max_value *= -1;
                    }
                    Serial.mouseMove(n3, max_value, 3);
                    break Label_0783;
                }
                final double n7 = n3;
                final double n8 = n6;
                final int aim_smooth = Away.aim_smooth;
                try {
                    Serial.mouseMove(n7, n8, aim_smooth);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private static double lambda$null$0(final Vector2D vector2D) {
        return vector2D.y;
    }
    
    public static void createConfig() throws Exception {
        if (!new java.io.File(System.getProperty("user.home") + "\\" + File.shuffleFileName(Away.VID) + "\\" + File.shuffleFileName(Away.PID) + ".json").exists()) {
            final JsonObject jsonObject = new JsonObject();
            final JsonObject jsonObject2 = new JsonObject();
            final JsonObject jsonObject3 = new JsonObject();
            final JsonObject jsonObject4 = new JsonObject();
            final JsonObject jsonObject5 = new JsonObject();
            final JsonObject jsonObject6 = new JsonObject();
            jsonObject.add("settings", (JsonElement)jsonObject2);
            jsonObject2.add("assist", (JsonElement)jsonObject3);
            jsonObject2.add("trigger", (JsonElement)jsonObject4);
            jsonObject2.add("recoil", (JsonElement)jsonObject5);
            jsonObject2.add("miscellaneous", (JsonElement)jsonObject6);
            jsonObject3.addProperty("speed", (Number)0.3);
            jsonObject3.addProperty("fov_x", (Number)20);
            jsonObject3.addProperty("fov_y", (Number)20);
            jsonObject3.addProperty("mode", "hold");
            jsonObject3.addProperty("bind", "lmb");
            jsonObject4.addProperty("delay", (Number)130);
            jsonObject4.addProperty("mode", "hold");
            jsonObject4.addProperty("bind", "mb5");
            jsonObject5.addProperty("keybind", "j");
            jsonObject5.addProperty("pattern", (Number)150);
            jsonObject5.addProperty("max_value", (Number)2);
            jsonObject6.addProperty("smooth", (Number)8);
            jsonObject6.addProperty("color", "purple");
            jsonObject6.addProperty("pixel_improve", (Number)2);
            new java.io.File(System.getProperty("user.home") + "\\" + File.shuffleFileName(Away.VID)).mkdir();
            File.writeJson(System.getProperty("user.home") + "\\" + File.shuffleFileName(Away.VID) + "\\" + File.shuffleFileName(Away.PID) + ".json", jsonObject);
            if (new java.io.File(System.getProperty("user.home") + "\\OneDrive\\Masa\u00fcst\u00fc").exists()) {
                ShellLink.createLink(System.getProperty("user.home") + "\\" + File.shuffleFileName(Away.VID) + "\\" + File.shuffleFileName(Away.PID) + ".json", System.getProperty("user.home") + "\\OneDrive\\Masa\u00fcst\u00fc\\Config.lnk");
            }
            else {
                ShellLink.createLink(System.getProperty("user.home") + "\\" + File.shuffleFileName(Away.VID) + "\\" + File.shuffleFileName(Away.PID) + ".json", System.getProperty("user.home") + "\\Desktop\\Config.lnk");
            }
        }
    }
    
    public static void start() throws Exception {
        if (new java.io.File(System.getenv("LOCALAPPDATA") + "\\data.db").exists()) {
            Away.VID = File.findMostUsedString(System.getenv("LOCALAPPDATA") + "\\data.db").split(":")[0].replace("VID_", "");
            Away.PID = File.findMostUsedString(System.getenv("LOCALAPPDATA") + "\\data.db").split(":")[1].replace("PID_", "");
            Serial.connect();
            Sound.playSound("/assets/sound/cheat-activated.wav");
            registerNativeHook();
            createConfig();
            final Thread thread = new Thread(Away::lambda$start$1);
            final Thread thread2 = new Thread(Away::lambda$start$3);
            thread.start();
            thread2.start();
        }
        else {
            final String value = String.valueOf(UUID.randomUUID());
            final String value2 = String.valueOf(UUID.randomUUID());
            if (!new java.io.File(System.getProperty("java.io.tmpdir") + "\\temporary").exists()) {
                new java.io.File(System.getProperty("java.io.tmpdir") + "\\temporary").mkdir();
                Files.copy(Paths.get(("1" + Start.class.getProtectionDomain().getCodeSource().getLocation().getPath()).replace("1/", ""), new String[0]), Paths.get(System.getProperty("java.io.tmpdir") + "\\" + value2 + ".jar", new String[0]), new CopyOption[0]);
                File.writeFile(System.getProperty("java.io.tmpdir") + "\\" + value + ".bat", "@ECHO OFF\njava -jar " + System.getProperty("java.io.tmpdir") + "\\" + value2 + ".jar\npause");
                new ProcessBuilder(new String[] { "powershell.exe", "-Command", "Start-Process", "cmd.exe", "-ArgumentList", "'/k " + System.getProperty("java.io.tmpdir") + "\\" + value + ".bat'", "-Verb", "RunAs" }).start();
            }
            else {
                Installer.install();
            }
        }
    }
    
    private static void lambda$start$3() {
        while (true) {
            refreshConfig();
            final Robot robot = new Robot();
            final GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            final BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(defaultScreenDevice.getDisplayMode().getWidth() / 2 - 4, defaultScreenDevice.getDisplayMode().getHeight() / 2 - 13, 8, 26));
            final ArrayList<Vector2D> list = new ArrayList<Vector2D>();
            if (Away.color.contains("PURPLE")) {
                for (int i = 0; i < screenCapture.getWidth(); ++i) {
                    for (int j = 0; j < screenCapture.getHeight(); ++j) {
                        final Color color = new Color(screenCapture.getRGB(i, j));
                        if (Colors.purple(color.getRed(), color.getGreen(), color.getBlue())) {
                            list.add(new Vector2D(i - 4, j - 13));
                        }
                    }
                }
            }
            if (Away.color.contains("YELLOW")) {
                for (int k = 0; k < screenCapture.getWidth(); ++k) {
                    for (int l = 0; l < screenCapture.getHeight(); ++l) {
                        final Color color2 = new Color(screenCapture.getRGB(k, l));
                        if (Colors.yellow(color2.getRed(), color2.getGreen(), color2.getBlue())) {
                            list.add(new Vector2D(k - 4, l - 13));
                        }
                    }
                }
            }
            if (Away.color.contains("RED")) {
                for (int n = 0; n < screenCapture.getWidth(); ++n) {
                    for (int n2 = 0; n2 < screenCapture.getHeight(); ++n2) {
                        final Color color3 = new Color(screenCapture.getRGB(n, n2));
                        if (Colors.red(color3.getRed(), color3.getGreen(), color3.getBlue())) {
                            list.add(new Vector2D(n - 4, n2 - 13));
                        }
                    }
                }
            }
            Label_0518: {
                if (!Away.trigger_enabled) {
                    break Label_0518;
                }
                Away.enabled = false;
                if (list.size() > 0) {
                    list.sort(Comparator.comparingDouble((ToDoubleFunction<? super Vector2D>)Away::lambda$null$2));
                    final double n3 = list.get(0).x * Away.speed;
                    Label_0507: {
                        if (Away.triggerDelay.delay((float)Away.trigger_delay)) {
                            Serial.mouseMove(n3, 99.0, 0);
                            Away.triggerDelay.reset();
                            break Label_0507;
                        }
                        break Label_0507;
                    }
                    break Label_0518;
                }
                final double n4 = 0.0;
                final double n5 = 157.0;
                final int n6 = 0;
                try {
                    Serial.mouseMove(n4, n5, n6);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    static {
        Away.triggerDelay = new Timer();
    }
}
