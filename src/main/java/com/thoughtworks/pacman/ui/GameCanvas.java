package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.ui.presenters.MazePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {
    private MazePresenter mazePresenter;

    public GameCanvas(Game game) {
        Maze maze = game.getMaze();
        mazePresenter = new MazePresenter(maze);
    }

    public Dimension getDimension() {
        return mazePresenter.getDimension();
    }

    public void initialize(JPanel panel) {
        setBounds(new Rectangle(getDimension()));
        panel.add(this);
        setIgnoreRepaint(true);
        createBufferStrategy(2);
    }

    public void draw() {
        BufferStrategy strategy = getBufferStrategy();
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

        g.setColor(Color.black);
        g.fill(new Rectangle(getDimension()));

        g.dispose();
        strategy.show();
    }
}
