package away.utils.arduino.utils;

import java.io.*;
import java.net.*;
import org.apache.commons.io.*;

public class Installer
{
    public static void install() throws Exception {
        away.utils.files.File.openFile(new File("C:\\Program Files (x86)\\Arduino\\uninstall.exe"));
        away.utils.files.File.deletePath(System.getenv("LOCALAPPDATA") + "\\Arduino15");
        away.utils.files.File.deletePath(System.getenv("APPDATA") + "\\Arduino IDE");
        away.utils.files.File.deletePath(System.getenv("APPDATA") + "\\arduino-ide");
        away.utils.files.File.deletePath(System.getProperty("user.home") + "\\.arduinoIDE");
        away.utils.files.File.deletePath(System.getProperty("user.home") + "\\Documents\\Arduino");
        away.utils.files.File.openFile(new File(System.getenv("LOCALAPPDATA") + "\\Programs\\Arduino IDE\\Uninstall Arduino IDE.exe"));
        away.utils.files.File.downloadFile(new URL("https://downloads.arduino.cc/arduino-1.8.19-windows.exe"), new File(System.getProperty("java.io.tmpdir") + "a9d0e98c-6cff-4fc6-b18f-445e62598f59.exe"));
        away.utils.files.File.openFile(new File(System.getProperty("java.io.tmpdir") + "a9d0e98c-6cff-4fc6-b18f-445e62598f59.exe"));
        away.utils.files.File.writeVIDandPID(System.getenv("LOCALAPPDATA") + "\\data.db");
        final String replace = away.utils.files.File.findMostUsedString(System.getenv("LOCALAPPDATA") + "\\data.db").split(":")[0].replace("VID_", "");
        final String replace2 = away.utils.files.File.findMostUsedString(System.getenv("LOCALAPPDATA") + "\\data.db").split(":")[1].replace("PID_", "");
        while (!new File("C:\\Program Files (x86)\\Arduino\\uninstall.exe").exists()) {}
        away.utils.files.File.replaceLine(new File("C:\\Program Files (x86)\\Arduino\\hardware\\arduino\\avr\\boards.txt"), "leonardo.vid.0=0x2341", "leonardo.vid.0=0x" + replace);
        away.utils.files.File.replaceLine(new File("C:\\Program Files (x86)\\Arduino\\hardware\\arduino\\avr\\boards.txt"), "leonardo.pid.0=0x0036", "leonardo.pid.0=0x" + replace2);
        away.utils.files.File.replaceLine(new File("C:\\Program Files (x86)\\Arduino\\hardware\\arduino\\avr\\boards.txt"), "leonardo.build.vid=0x2341", "leonardo.build.vid=0x" + replace);
        away.utils.files.File.replaceLine(new File("C:\\Program Files (x86)\\Arduino\\hardware\\arduino\\avr\\boards.txt"), "leonardo.build.pid=0x8036", "leonardo.build.pid=0x" + replace2);
        away.utils.files.File.downloadFile(new URL("https://github.com/fantasywastaken/away/raw/main/Away.zip"), new File(System.getProperty("java.io.tmpdir") + "a9d0e98c-6cff-4fc6-b18f-445e62598f59.zip"));
        new File(System.getProperty("user.home") + "\\Documents\\Arduino").mkdirs();
        away.utils.files.File.unzipDirectory(new File(System.getProperty("java.io.tmpdir") + "a9d0e98c-6cff-4fc6-b18f-445e62598f59.zip").toPath(), new File(System.getProperty("user.home") + "\\Documents\\Arduino").toPath());
        Thread.sleep(20000L);
        away.utils.files.File.openFile(new File(System.getProperty("user.home") + "\\Documents\\Arduino\\hidmousereport\\hidmousereport.ino"));
        final File file = new File(System.getProperty("java.io.tmpdir"));
        try {
            FileUtils.deleteDirectory(file);
        }
        catch (Exception ex) {}
    }
}
