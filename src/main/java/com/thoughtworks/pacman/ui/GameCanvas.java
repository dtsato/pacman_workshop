package com.thoughtworks.pacman.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

public class GameCanvas extends Canvas {
    private final GamePresenter gamePresenter;

    public GameCanvas(Game game) {
        gamePresenter = new GamePresenter(game);
    }

    public Dimension getDimension() {
        return gamePresenter.getDimension();
    }

    public void initialize(JPanel panel) {
        setBounds(new Rectangle(gamePresenter.getDimension()));
        panel.add(this);
        setIgnoreRepaint(true);
        createBufferStrategy(2);
    }

    public void draw() {
        BufferStrategy strategy = getBufferStrategy();
        Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics.setColor(Color.black);
        graphics.fill(new Rectangle(gamePresenter.getDimension()));

        gamePresenter.draw(graphics);

        graphics.dispose();
        strategy.show();
    }
}
