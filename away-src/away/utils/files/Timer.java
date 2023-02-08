package away.utils.files;

public class Timer
{
    public static long prevMS;
    
    public Timer() {
        Timer.prevMS = 0L;
    }
    
    public void reset() {
        Timer.prevMS = this.getTime();
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean delay(final float n) {
        return this.getTime() - Timer.prevMS >= n;
    }
}
