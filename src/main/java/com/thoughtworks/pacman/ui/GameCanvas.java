package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

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

        graphics.setColor(Color.black);
        graphics.fill(new Rectangle(gamePresenter.getDimension()));

        gamePresenter.draw(graphics);

        graphics.dispose();
        strategy.show();
    }
}
