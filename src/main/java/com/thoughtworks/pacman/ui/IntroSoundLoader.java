
package com.thoughtworks.pacman.ui;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class IntroSoundLoader implements Runnable {
Clip clip ;

public void run(){
       playIntro();
       try {
         Thread.currentThread().stop();    
       } catch (Exception e) {
        
     }
    }

         
public void setStop(){
    clip.stop();
} 
    public void playIntro(){
       File p = new File("pacman_workshop");
       String a = (p.getAbsolutePath().substring(0, p.getAbsolutePath().indexOf("pacman_workshop")))+ "pacman_workshop/";// to add to relative path
       File musicPath = new File(a+"src/main/resources/com/thoughtworks/pacman/ui/pacman_beginning.wav");
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
              System.out.println("exception play intro");
            }
   }


    


}

