package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import com.thoughtworks.pacman.ui.FinalSoundLoader;
import java.util.concurrent.locks.ReentrantLock;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class LostScreen implements Screen {
    static final Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "gameOver.png");
    private  ReentrantLock lock = new ReentrantLock();
    private final Dimension dimension;
    private final Game game;
    private boolean startGame;
    private FinalSoundLoader FinalsoundLoader = new FinalSoundLoader();
    private Thread threadSounds = new Thread(FinalsoundLoader, "FinasoundLoader");
    private boolean check = true ;

    public LostScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
    }

    public void draw(Graphics2D graphics) {
        int height = LOST_SCREEN_IMAGE.getHeight(null) * dimension.width / LOST_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(LOST_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() {
        if (startGame) {
            return new IntroScreen(game);
        }
        return this;
    }

    public void play(){
        try {
            lock.lock();
            threadSounds.start();
            lock.unlock();
        } catch (Exception e) {}
    }

    public void check(){
        if ( startGame &&check) {
            check = false ;
            FinalsoundLoader.setStop();
        }
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
