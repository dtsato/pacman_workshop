package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class WinScreen implements Screen {
    static final Image WIN_SCREEN_IMAGE = Toolkit.getDefaultToolkit().getImage(Screen.class.getResource("winScreen.jpg"));

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

    public WinScreen(Game game) {
        this.dimension = game.getDimension();
        this.game = game;
        this.startGame = false;
    }

    @Override
    public void draw(Graphics2D graphics) {
        int height = WIN_SCREEN_IMAGE.getHeight(null) * dimension.width / WIN_SCREEN_IMAGE.getWidth(null);
        graphics.drawImage(WIN_SCREEN_IMAGE, 0, 0, dimension.width, height, null);
    }

    @Override
    public Screen getNextScreen() {
        if (startGame) {
            return new IntroScreen(game);
        }
        return this;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
