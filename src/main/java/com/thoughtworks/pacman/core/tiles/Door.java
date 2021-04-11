package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;

public class Door extends Tile {

    public Door(TileCoordinate coordinate, String value) {
        super(coordinate);
    }

    public Door(TileCoordinate coordinate) {
        super(coordinate);
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public <T> T visit(TileVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public <T> T visit(TileVisitor<T> visitor, int xOffSet) {
        return visitor.visit(this, xOffSet);
    }

    @Override
    public String toString() {
        return "-";
    }
}
