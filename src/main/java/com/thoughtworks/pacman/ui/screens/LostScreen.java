package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class LostScreen implements Screen {
    static final Image LOST_SCREEN_IMAGE = Toolkit.getDefaultToolkit().getImage(Screen.class.getResource("gameOver.jpg"));

    private final Dimension dimension;
    private final Game game;
    private boolean startGame;

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

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
