package com.thoughtworks.pacman.core;

import java.awt.Point;

public class SpacialCoordinate {
    private final int x;
    private final int y;

    public SpacialCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SpacialCoordinate add(SpacialCoordinate spacialCoordinate) {
        return new SpacialCoordinate(x + spacialCoordinate.x, y + spacialCoordinate.y);
    }

    public SpacialCoordinate times(int scale) {
        return new SpacialCoordinate(x * scale, y * scale);
    }

    public Point toPoint() {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return String.format("[%2d,%2d]", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpacialCoordinate spacialCoordinate = (SpacialCoordinate) o;

        if (x != spacialCoordinate.x) return false;
        if (y != spacialCoordinate.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
