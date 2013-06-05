package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Position;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PacmanPresenter implements Presenter {
    private final Pacman pacman;

    public PacmanPresenter(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.yellow);
        Rectangle bounds = getBounds();
        graphics.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, 220, 280);
    }

    public Rectangle getBounds() {
        final Position position = pacman.getPosition().times(TILE_SIZE);
        return new Rectangle(position.toPoint(), new Dimension(TILE_SIZE, TILE_SIZE));
    }
}
