package com.thoughtworks.pacman.core;

public abstract class Tile {
    public static int SIZE = 16;

    private final TileCoordinate coordinate;

    public Tile(TileCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public SpacialCoordinate getCenter() {
        return coordinate.toSpacialCoordinate();
    }

    public abstract boolean isMovable();

    public abstract <T> T visit(TileVisitor<T> visitor);
}
