package com.thoughtworks.pacman.ui.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

public class TwoPlayerEndScreen implements Screen{

    private final Dimension dimension1;
    private final Dimension dimension2;
    private final Game game1;
    private boolean startGame;
    private final Image game1Image;
    private final Image game2Image;
    private final int height;

    public TwoPlayerEndScreen(Game game1, Game game2) {
        this.dimension1 = game1.getDimension();
        this.dimension2 = game2.getDimension();
        this.game1 = game1;
        this.startGame = false;

        Image WIN_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "winScreen.jpg");
        Image LOST_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "gameOver.png");
        this.game1Image = game1.won() ? WIN_SCREEN_IMAGE : LOST_SCREEN_IMAGE;
        this.game2Image = game2.won() ? WIN_SCREEN_IMAGE : LOST_SCREEN_IMAGE;
        this.height = WIN_SCREEN_IMAGE.getHeight(null) * dimension1.width / WIN_SCREEN_IMAGE.getWidth(null);
    }

    public void draw(Graphics2D graphics) {
        graphics.drawImage(game2Image, 0, 0, dimension2.width, height, null);
        graphics.drawImage(game1Image, dimension2.width + Tile.SIZE * 2, 0, dimension1.width, height, null);
    }

    public Screen getNextScreen() {
        if (startGame) {
            IntroScreen introScreen = new IntroScreen(game1);
            introScreen.setMode(true);
            return introScreen;
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        startGame = true;
    }
}
