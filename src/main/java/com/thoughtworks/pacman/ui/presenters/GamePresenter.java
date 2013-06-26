package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Graphics2D;

public class GamePresenter implements Presenter {
    private final MazePresenter mazePresenter;
    private final PacmanPresenter pacmanPresenter;
    private final GhostPresenter[] ghostPresenters;

    public GamePresenter(Game game) {
        mazePresenter = new MazePresenter(game.getMaze());
        pacmanPresenter = new PacmanPresenter(game.getPacman());
        ghostPresenters = new GhostPresenter[] {
                new GhostPresenter(game.getBlinky()),
                new GhostPresenter(game.getPinky()),
                new GhostPresenter(game.getInky()),
                new GhostPresenter(game.getClyde())
        };
    }

    public void draw(Graphics2D graphics) {
        mazePresenter.draw(graphics);
        pacmanPresenter.draw(graphics);
        if (!isDying()) {
            for (GhostPresenter ghostPresenter : ghostPresenters) {
                ghostPresenter.draw(graphics);
            }
        }
    }

    public boolean isDying() {
        return pacmanPresenter.isDying();
    }
}