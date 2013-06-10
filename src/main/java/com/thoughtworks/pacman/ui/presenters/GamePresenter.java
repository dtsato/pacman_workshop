package com.thoughtworks.pacman.ui.presenters;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Presenter;

public class GamePresenter implements Presenter {
    private final MazePresenter mazePresenter;
    private final PacmanPresenter pacmanPresenter;

    public GamePresenter(Game game) {
        mazePresenter = new MazePresenter(game.getMaze());
        pacmanPresenter = new PacmanPresenter(game.getPacman());
    }

    public Dimension getDimension() {
        return mazePresenter.getDimension();
    }

    @Override
    public void draw(Graphics2D graphics) {
        mazePresenter.draw(graphics);
        pacmanPresenter.draw(graphics);
    }
}