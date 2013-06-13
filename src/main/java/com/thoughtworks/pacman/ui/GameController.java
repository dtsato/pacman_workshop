package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    private Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void initialize(GameCanvas canvas) {
        canvas.addKeyListener(this);
        canvas.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                game.getPacman().setNextDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                game.getPacman().setNextDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                game.getPacman().setNextDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                game.getPacman().setNextDirection(Direction.DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
