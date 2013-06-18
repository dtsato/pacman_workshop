package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GhostPresenter implements Presenter {
    private final Ghost ghost;
    private final Color color;

    public GhostPresenter(Ghost ghost, Color color) {
        this.ghost = ghost;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        Rectangle bounds = getBounds();
        graphics.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        int radius = Tile.SIZE / 2;
        SpacialCoordinate upperLeft = ghost.getCenter().add(new SpacialCoordinate(-radius, -radius));
        return new Rectangle(upperLeft.toPoint(), new Dimension(Tile.SIZE, Tile.SIZE));
    }
}
