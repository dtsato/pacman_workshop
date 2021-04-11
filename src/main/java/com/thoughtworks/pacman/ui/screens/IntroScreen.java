package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "titleScreen.jpg");

    private final Dimension dimension;
    private boolean startGame;
    private boolean isTwoPlayerMode;

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        this.startGame = false;
        this.isTwoPlayerMode = false;
    }

    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    public Screen getNextScreen() throws Exception {
        if (startGame) {
            if(isTwoPlayerMode){
                return (Screen) new TwoPlayerGameScreen();
            }
            else {
                return new GameScreen();
            }
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }

    public void setMode(boolean mode) {
        isTwoPlayerMode = mode;
    }
}
