package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class IntroScreen implements Screen {
    static final Image TITLE_SCREEN_IMAGE = Toolkit.getDefaultToolkit().getImage(Screen.class.getResource("titleScreen.jpg"));

    private final Dimension dimension;
    private boolean startGame;

    public IntroScreen(Game game) {
        this.dimension = game.getDimension();
        this.startGame = false;
    }

    @Override
    public void draw(Graphics2D graphics) {
        int height = TITLE_SCREEN_IMAGE.getHeight(null) * dimension.width / TITLE_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(TITLE_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    @Override
    public Screen getNextScreen() throws Exception {
        if (startGame) {
            return new GameScreen();
        }
        return this;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
