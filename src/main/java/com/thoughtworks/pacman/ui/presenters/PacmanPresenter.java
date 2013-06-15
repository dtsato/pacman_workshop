package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PacmanPresenter implements Presenter {
    private final Pacman pacman;
    private long lastFrame;

    public PacmanPresenter(Pacman pacman) {
        this.pacman = pacman;
        this.lastFrame = 0;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.yellow);
        Rectangle bounds = getBounds();
        graphics.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, pacman.getDirection().getStartAngle(), getArcAngle());
    }

    public int getArcAngle() {
        return lastFrame++ % 10 < 5 ? 360 : 280;
    }

    public Rectangle getBounds() {
        int radius = Tile.SIZE / 2;
        SpacialCoordinate upperLeft = pacman.getCenter().add(new SpacialCoordinate(-radius, -radius));
        return new Rectangle(upperLeft.toPoint(), new Dimension(Tile.SIZE, Tile.SIZE));
    }
}
