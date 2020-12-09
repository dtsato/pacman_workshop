
package com.thoughtworks.pacman.ui;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BackgroundSoundLoader implements Runnable {
private Boolean stop = false;
Clip clip ;
    public void run(){
    playBackground();  

       try {
         Thread.currentThread().stop();    
       } catch (Exception e) {
            
       }
       
     }

         

public boolean setStop(){
    this.stop = true;
    clip.stop();
    return true;
} 

    public void playBackground(){

        File musicPath = new File("src/main/resources/com/thoughtworks/pacman/ui/Pac-man-theme-remix-By-Arsenic1987.wav");
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
              System.out.println("exception play backgorund");
            }

    }

}

