package com.thoughtworks.pacman.core;

public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    private final int x;
    private final int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SpacialCoordinate delta() {
        return new SpacialCoordinate(x, y);
    }

    public TileCoordinate tileDelta() {
        return new TileCoordinate(x, y);
    }
}
