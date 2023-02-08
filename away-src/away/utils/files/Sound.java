package away.utils.files;

import javax.sound.sampled.*;
import java.io.*;

public class Sound
{
    public static void playSound(final String s) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        final Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(Sound.class.getResource(s)));
        clip.start();
    }
}
