package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.presenters.MazePresenter;

import java.awt.*;
import java.io.Serializable;

public class GamePresenter implements Presenter {
    private final MazePresenter mazePresenter;

    public GamePresenter(Game game) {
        mazePresenter = new MazePresenter(game.getMaze());
    }

    public Dimension getDimension() {
        return mazePresenter.getDimension();
    }

    @Override
    public void draw(Graphics2D graphics) {
        mazePresenter.draw(graphics);
    }
}