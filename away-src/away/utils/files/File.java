package away.utils.files;

import java.nio.file.attribute.*;
import java.util.zip.*;
import org.json.*;
import java.nio.file.*;
import java.util.function.*;
import com.google.gson.*;
import java.awt.*;
import org.apache.commons.io.*;
import java.nio.charset.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class File
{
    public static void unzipDirectory(final Path path, final Path path2) throws Exception {
        if (!Files.exists(path, new LinkOption[0])) {
            return;
        }
        deleteDirectoryRecursively(path2);
        Files.createDirectory(path2, (FileAttribute<?>[])new FileAttribute[0]);
        final ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(path, new OpenOption[0]));
        ZipEntry nextEntry;
        while ((nextEntry = zipInputStream.getNextEntry()) != null) {
            final Path resolve = path2.resolve(nextEntry.getName());
            if (nextEntry.isDirectory()) {
                Files.createDirectory(resolve, (FileAttribute<?>[])new FileAttribute[0]);
            }
            else {
                if (!Files.exists(resolve.getParent(), new LinkOption[0])) {
                    Files.createDirectories(resolve.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
                }
                Files.copy(zipInputStream, resolve, new CopyOption[0]);
            }
        }
        zipInputStream.close();
    }
    
    public static void writeVIDandPID(final String s) throws Exception {
        if (!new java.io.File(s).exists()) {
            final Process exec = Runtime.getRuntime().exec("PowerShell -Command \"& {Get-PnpDevice | Select-Object Status,Class,FriendlyName,InstanceId | ConvertTo-Json}\"");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            final JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); ++i) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                final String string = jsonObject.getString("InstanceId");
                if (string.startsWith("HID") && string.contains("VID_") && jsonObject.getString("FriendlyName") != null && (jsonObject.getString("FriendlyName").contains("fare") || jsonObject.getString("FriendlyName").contains("mouse"))) {
                    final String s2 = string.split("\\\\")[1].split("&")[0];
                    final String s3 = string.split("\\\\")[1].split("&")[1];
                    final FileWriter fileWriter = new FileWriter(s, true);
                    fileWriter.append((CharSequence)s2).append((CharSequence)":").append((CharSequence)s3).append((CharSequence)"\n");
                    fileWriter.close();
                }
            }
            bufferedReader.close();
            exec.destroy();
        }
    }
    
    private static void deleteDirectoryRecursively(final Path path) throws Exception {
        if (Files.exists(path, new LinkOption[0])) {
            Files.walk(path, new FileVisitOption[0]).sorted(Comparator.reverseOrder()).map((Function<? super Path, ?>)Path::toFile).forEach(java.io.File::delete);
        }
    }
    
    public static String shuffleFileName(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            char c = charArray[i];
            if (c >= '0' && c <= '9') {
                if (c > '5') {
                    c -= '\u0003';
                }
                else {
                    c += '\u0003';
                }
            }
            if (c >= 'a' && c <= 'z') {
                if (c > 'm') {
                    c -= '\r';
                }
                else {
                    c += '\r';
                }
            }
            else if (c >= 'A' && c <= 'Z') {
                if (c > 'M') {
                    c -= '\r';
                }
                else {
                    c += '\r';
                }
            }
            charArray[i] = c;
        }
        return new String(charArray);
    }
    
    public static void writeJson(final String s, final JsonObject... array) throws IOException {
        final Gson create = new GsonBuilder().setPrettyPrinting().create();
        final FileWriter fileWriter = new FileWriter(s);
        fileWriter.write(create.toJson((Object)array));
        fileWriter.close();
    }
    
    public static void writeFile(final String s, final String s2) throws IOException {
        final FileWriter fileWriter = new FileWriter(s, false);
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(Objects.requireNonNull(s2));
        bufferedWriter.close();
        fileWriter.close();
    }
    
    public static void openFile(final java.io.File file) throws Exception {
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        }
    }
    
    public static void deletePath(final String s) throws Exception {
        if (new java.io.File(s).exists()) {
            FileUtils.deleteDirectory(new java.io.File(s));
        }
    }
    
    public static String findMostUsedString(final String s) throws Exception {
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(s));
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            hashMap.put(line, hashMap.getOrDefault(line, 0) + 1);
        }
        int intValue = 0;
        String s2 = "";
        for (final Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > intValue) {
                intValue = entry.getValue();
                s2 = entry.getKey();
            }
        }
        return s2;
    }
    
    public static void replaceLine(final java.io.File file, final String s, final String s2) throws Exception {
        final ArrayList<String> list = new ArrayList<String>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
        for (int i = 0; i < list.size(); ++i) {
            if (((String)list.get(i)).equals(s)) {
                list.set(i, s2);
                break;
            }
        }
        Files.write(file.toPath(), list, StandardCharsets.UTF_8, new OpenOption[0]);
    }
    
    public static void downloadFile(final URL url, final java.io.File file) throws Exception {
        if (!file.exists()) {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            Throwable t = null;
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(file.toPath(), new OpenOption[0]));
            Throwable t2 = null;
            final byte[] array = new byte[4096];
            int read;
            while ((read = bufferedInputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
            }
            final BufferedOutputStream bufferedOutputStream2 = bufferedOutputStream;
            Label_0173: {
                try {
                    bufferedOutputStream2.flush();
                    bufferedInputStream.close();
                    if (bufferedOutputStream == null) {
                        break Label_0173;
                    }
                    if (t2 != null) {
                        final BufferedOutputStream bufferedOutputStream3 = bufferedOutputStream;
                        try {
                            bufferedOutputStream3.close();
                            break Label_0173;
                        }
                        catch (Throwable t3) {
                            t2.addSuppressed(t3);
                            break Label_0173;
                        }
                        break Label_0173;
                    }
                    bufferedOutputStream.close();
                    break Label_0173;
                }
                catch (Throwable t4) {
                    t2 = t4;
                    throw t4;
                }
                try {
                    throw;
                    // iftrue(Label_0249:, bufferedInputStream == null)
                    // iftrue(Label_0199:, t == null)
                    while (true) {
                        final BufferedInputStream bufferedInputStream2 = bufferedInputStream;
                        try {
                            bufferedInputStream2.close();
                        }
                        catch (Throwable t5) {
                            t.addSuppressed(t5);
                        }
                        return;
                        continue;
                    }
                    Label_0199: {
                        bufferedInputStream.close();
                    }
                    return;
                }
                catch (Throwable t6) {
                    t = t6;
                    throw t6;
                }
            }
            throw;
        }
        Label_0249:;
    }
}
