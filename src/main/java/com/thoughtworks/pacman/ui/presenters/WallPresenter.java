package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.Presenter;
import sun.jvm.hotspot.memory.SpaceClosure;

import java.awt.*;

public class WallPresenter implements Presenter {
    private final TileCoordinate tileCoordinate;

    public WallPresenter(Wall wall, TileCoordinate tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.blue);
        graphics.draw(new Rectangle(getTileCoordinate(), getDimension()));
    }

    public Point getTileCoordinate() {
        int delta = Tile.SIZE / 2;
        return tileCoordinate.toSpacialCoordinate().add(new SpacialCoordinate(-delta, -delta)).toPoint();
    }

    public Dimension getDimension() {
        return new Dimension(Tile.SIZE, Tile.SIZE);
    }
}
