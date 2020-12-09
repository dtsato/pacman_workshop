package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.ReentrantLock;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.WinnerSoundLoader;

public class WinScreen implements Screen {
    static final Image WIN_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "winScreen.jpg");
    private  ReentrantLock lock = new ReentrantLock();
    private final Dimension dimension;
    private final Game game;
    private boolean startGame;
    private WinnerSoundLoader WinnerSoundLoader = new WinnerSoundLoader();
    private Thread threadSounds = new Thread(WinnerSoundLoader, "WinnerSoundLoader");

    public WinScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        int height = WIN_SCREEN_IMAGE.getHeight(null) * dimension.width / WIN_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(WIN_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() {
        if (startGame) {
            WinnerSoundLoader.setStop();
            return new IntroScreen(game);
        }
        return this;
    }

    public void play (){
        try {
        if(!startGame){
            lock.lock();
            threadSounds.start();
            lock.unlock();
    
         
        } }catch (Exception e) {
        }
    

    }
    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
