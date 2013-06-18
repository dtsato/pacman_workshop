package com.thoughtworks.pacman.ui.presenters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Presenter;

public class GamePresenter implements Presenter {
    private final MazePresenter mazePresenter;
    private final PacmanPresenter pacmanPresenter;
    private final GhostPresenter blinkyPresenter;
    private final GhostPresenter pinkyPresenter;
    private final GhostPresenter inkyPresenter;
    private final GhostPresenter clydePresenter;

    public GamePresenter(Game game) {
        mazePresenter = new MazePresenter(game.getMaze());
        pacmanPresenter = new PacmanPresenter(game.getPacman());
        blinkyPresenter = new GhostPresenter(game.getBlinky(), Color.red);
        pinkyPresenter = new GhostPresenter(game.getPinky(), Color.magenta);
        inkyPresenter = new GhostPresenter(game.getInky(), Color.cyan);
        clydePresenter = new GhostPresenter(game.getClyde(), Color.orange);
    }

    public Dimension getDimension() {
        return mazePresenter.getDimension();
    }

    @Override
    public void draw(Graphics2D graphics) {
        mazePresenter.draw(graphics);
        blinkyPresenter.draw(graphics);
        pinkyPresenter.draw(graphics);
        inkyPresenter.draw(graphics);
        clydePresenter.draw(graphics);
        pacmanPresenter.draw(graphics);
    }
}