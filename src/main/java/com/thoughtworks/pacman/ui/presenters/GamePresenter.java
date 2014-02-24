package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public class GamePresenter implements Presenter {
    private final MazePresenter mazePresenter;
    private final PacmanPresenter pacmanPresenter;
    private final List<GhostPresenter> ghostPresenters;

    public GamePresenter(Game game) {
        mazePresenter = new MazePresenter(game.getMaze());
        pacmanPresenter = new PacmanPresenter(game.getPacman());
        ghostPresenters = new LinkedList<>();
        for (Ghost ghost : game.getGhosts()) {
            ghostPresenters.add(new GhostPresenter(ghost));
        }
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