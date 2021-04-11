package com.thoughtworks.pacman.ui.presenters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.core.tiles.WallType;
import com.thoughtworks.pacman.ui.Presenter;

public class DoorPresenter implements Presenter {

    private final Door door;

    public DoorPresenter(Door door) {
        this.door = door;
    }

    public DoorPresenter(Door door, int xOffset) {
        TileCoordinate coordinate = door.getCoordinate();
        this.door = new Door(new TileCoordinate(coordinate.getX() + xOffset, coordinate.getY()));
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.pink);
        graphics.setStroke(new BasicStroke(2.5f));
        graphics.draw(WallType.HORIZONTAL.getShape(door.getCenter()));
    }
}
