
package com.thoughtworks.pacman.ui;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FinalSoundLoader implements Runnable {
private Boolean stop = false;
Clip clip ;
    public void run(){
       playFinalCountdown();
       try {
         Thread.currentThread().stop();    
       } catch (Exception e) {
       }
    }
     

         
public void setStop(){
    this.stop = true;
    clip.stop();

} 

    public void playFinalCountdown(){

        File musicPath = new File("/home/irem/pacman_workshop/src/main/resources/com/thoughtworks/pacman/ui/pacman_death.wav");
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
                 e.printStackTrace();
              System.out.println("exception play final");
            }

    }


}

