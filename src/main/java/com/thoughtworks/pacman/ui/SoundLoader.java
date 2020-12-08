
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader {
    
    public void play(){
    File musicPath = new File("/home/irem/pacman_workshop/src/main/resources/com/thoughtworks/pacman/ui/pacman_beginning.wav");
    if (musicPath.exists()){
     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
     Clip clip = AudioSystem.getClip();
     clip.start();
     clip.loop(Clip.LOOP_CONTINUOUSLY);
    }else {
        System.out.println("sorry");
    }
}

}