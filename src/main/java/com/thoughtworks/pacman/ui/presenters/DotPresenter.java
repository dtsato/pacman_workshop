package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Position;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class DotPresenter implements Presenter {
    private static final int DIAMETER = 4;

    private final Position position;

    public DotPresenter(Dot dot, Position position) {
        this.position = position;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.white);
        graphics.fill(getBounds());
    }

    public Rectangle getBounds() {
        int delta = (Tile.SIZE - DIAMETER) / 2;
        Point point = position.times(Tile.SIZE).add(new Position(delta, delta)).toPoint();
        return new Rectangle(point.x, point.y, DIAMETER, DIAMETER);
    }
}
