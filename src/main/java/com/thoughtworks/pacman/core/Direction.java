package com.thoughtworks.pacman.core;

import java.util.EnumSet;

public enum Direction {
    UP(0, -1, 130), DOWN(0, 1, 310), LEFT(-1, 0, 220), RIGHT(1, 0, 40), NONE(0, 0, 0);

    private final int x;
    private final int y;
    private final int startAngle;

    private Direction(int x, int y, int startAngle) {
        this.x = x;
        this.y = y;
        this.startAngle = startAngle;
    }

    public SpacialCoordinate delta() {
        return new SpacialCoordinate(x, y);
    }

    public TileCoordinate tileDelta() {
        return new TileCoordinate(x, y);
    }

    public int getStartAngle() {
        return startAngle;
    }

    public static EnumSet<Direction> validMovements() {
        return EnumSet.of(UP, DOWN, LEFT, RIGHT);
    }
}
