
package com.thoughtworks.pacman.ui;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class WinnerSoundLoader implements Runnable {
private Boolean stop = false;
Clip clip ;
    

public void run(){
     playWon();
    try {
         Thread.currentThread().stop();    
       } catch (Exception e) {
      }
       
}

         
public void setStop(){
    this.stop = true;
    clip.stop();

}

    public void playWon(){
        File musicPath = new File("src/main/resources/com/thoughtworks/pacman/ui/The Final Countdown .wav");
             try {
                if (musicPath.exists()){
                    
                     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                     clip = AudioSystem.getClip();
                     clip.open(audioInputStream);
                     clip.start();
                     clip.loop(Clip.LOOP_CONTINUOUSLY);
            
                }else {
                     System.out.println("sorry");
                   }
            } catch (Exception e) {
              System.out.println("exception play won");
            }

    }

   

}

