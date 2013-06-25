package com.thoughtworks.pacman.ui.presenters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.ui.Presenter;

public class DoorPresenter implements Presenter {

    private final Door door;

    public DoorPresenter(Door door) {
        this.door = door;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.white);
        graphics.fill(new Rectangle(getTileCoordinate(), getDimension()));
    }

    public Point getTileCoordinate() {
        int delta = Tile.SIZE / 2;
        return door.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
    }

    public Dimension getDimension() {
        return new Dimension(Tile.SIZE, Tile.SIZE);
    }
}
