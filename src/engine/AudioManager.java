package engine; // חייב להיות engine!

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private Map<String, Clip> sounds = new HashMap<>();

    public void loadSound(String name, String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            if (is == null) {
                System.err.println("Error: Could not find file at " + path);
                return;
            }
            // התיקון שמונע את השגיאה שהייתה לך
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            sounds.put(name, clip);
            System.out.println("Successfully loaded: " + name);
        } catch (Exception e) {
            System.err.println("Error loading sound: " + name);
            e.printStackTrace();
        }
    }

    public void play(String name) {
        Clip clip = sounds.get(name);
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}