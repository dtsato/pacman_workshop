package com.thoughtworks.pacman.core.tiles;

import java.awt.Shape;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.TileVisitor;

public class Wall extends Tile {

    private final WallType type;

    public Wall(TileCoordinate coordinate, String value) {
        super(coordinate);
        this.type = WallType.fromMazeTile(value);
    }

    public Wall(TileCoordinate coordinate, WallType type) {
        super(coordinate);
        this.type = type;
    }

    public Wall(TileCoordinate coordinate) {
        super(coordinate);
        this.type = WallType.BOTTOM_LEFT;
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
        return "+";
    }

    public Shape getShape() {
        return type.getShape(this.getCenter());
    }

    public WallType getType() {
        return type;
    }
}
