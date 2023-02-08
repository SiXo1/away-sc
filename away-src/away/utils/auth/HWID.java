package away.utils.auth;

import java.security.*;

public class HWID
{
    private static final char[] hexArray;
    
    public static byte[] generateHWID() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("MD5").digest((System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("NUMBER_OF_PROCESSORS")).getBytes());
    }
    
    public static String getHWID() throws NoSuchAlgorithmException {
        return bytesToHex(generateHWID());
    }
    
    static {
        hexArray = "0123456789ABCDEF".toCharArray();
    }
    
    public static String bytesToHex(final byte[] array) {
        final char[] array2 = new char[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            array2[i * 2] = HWID.hexArray[n >>> 4];
            array2[i * 2 + 1] = HWID.hexArray[n & 0xF];
        }
        return new String(array2);
    }
}
