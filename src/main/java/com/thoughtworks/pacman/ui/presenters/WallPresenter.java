package com.thoughtworks.pacman.ui.presenters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.Presenter;

public class WallPresenter implements Presenter {
    private final Wall wall;

    public WallPresenter(Wall wall) {
        this.wall = wall;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.blue);
        graphics.setStroke(new BasicStroke(2.5f));
        graphics.draw(wall.getShape());
    }

    public Point getTileCoordinate() {
        int delta = Tile.SIZE / 2;
        return wall.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
    }

    public Dimension getDimension() {
        return new Dimension(Tile.SIZE, Tile.SIZE);
    }
}
