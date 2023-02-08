package away.utils.files;

import java.awt.*;

public class Colors
{
    public static boolean purple(final int n, final int n2, final int n3) {
        return (n2 < 190 && Math.abs(n - n3) <= 8 && n - n2 >= 50 && n3 - n2 >= 50 && n >= 105 && n3 >= 105 && n2 >= 140) || (Math.abs(n - n3) <= 13 && n - n2 >= 60 && n3 - n2 >= 60 && n >= 110 && n3 >= 100);
    }
    
    public static boolean yellow(final int n, final int n2, final int n3) {
        return (n >= 225 || n2 >= 175 || n3 >= 150) && n >= 200 && n2 > 200 && n2 < 256 && n3 > 0 && n3 < 100;
    }
    
    public static boolean red(final int n, final int n2, final int n3) {
        return new Color(220, 0, 0).getRed() <= n && n <= new Color(255, 120, 120).getRed() && new Color(220, 0, 0).getGreen() <= n2 && n2 <= new Color(255, 120, 120).getGreen() && new Color(220, 0, 0).getBlue() <= n3 && n3 <= new Color(255, 120, 120).getBlue();
    }
}
