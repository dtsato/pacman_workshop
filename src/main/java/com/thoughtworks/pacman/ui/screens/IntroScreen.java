package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.ReentrantLock;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.IntroSoundLoader;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");
    private  ReentrantLock lock = new ReentrantLock();
    private final Dimension dimension;
    private boolean startGame;
    private IntroSoundLoader IntroSoundLoader = new IntroSoundLoader();
    private Thread threadSounds = new Thread(IntroSoundLoader, "IntroSoundLoader");
    private boolean check = true;

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            check = true ;
            return new GameScreen();
        }
        return this;
    }
    
    public void play (){
        try {
            lock.lock();
            threadSounds.start();
            lock.unlock();
        }catch (Exception e) {}
    }

    public void check(){
        if (startGame &&check) {
           check = false ;   
           IntroSoundLoader.setStop();
        }
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }

}
