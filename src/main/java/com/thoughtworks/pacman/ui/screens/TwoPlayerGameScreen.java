package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TwoPlayerGameScreen implements Screen {
    private final Game game1;
    private final Game game2;
    private final GamePresenter gamePresenter1;
    private final GamePresenter gamePresenter2;
    private long lastFrameAt;

    public TwoPlayerGameScreen() throws Exception {
        this(new Game(), new Game());
    }

    private TwoPlayerGameScreen(Game game1, Game game2) {
        this(game1, game2, new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2), new GamePresenter(game2));
    }

    TwoPlayerGameScreen(Game game1, Game game2, GamePresenter gamePresenter1, GamePresenter gamePresenter2) {
        this.game1 = game1;
        this.game2 = game2;
        this.gamePresenter1 = gamePresenter1;
        this.gamePresenter2 = gamePresenter2;
        this.lastFrameAt = System.currentTimeMillis();
    }

    public void draw(Graphics2D graphics) {
        long currentFrameAt = System.currentTimeMillis();
        long timeDelta = currentFrameAt - lastFrameAt;

        game1.advance(timeDelta);
        gamePresenter1.draw(graphics);

        game2.advance(timeDelta);
        gamePresenter2.draw(graphics);

        lastFrameAt = currentFrameAt;
    }

    public Screen getNextScreen() {
        if ((game1.won() || game1.lost()) && (game2.won() || game2.lost())) {
            return new TwoPlayerEndScreen(game1, game2);
        }
        return this;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                game1.getPacman().setNextDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                game1.getPacman().setNextDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                game1.getPacman().setNextDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                game1.getPacman().setNextDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_A:
                game2.getPacman().setNextDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_D:
                game2.getPacman().setNextDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_W:
                game2.getPacman().setNextDirection(Direction.UP);
                break;
            case KeyEvent.VK_S:
                game2.getPacman().setNextDirection(Direction.DOWN);
                break;
        }
    }
}