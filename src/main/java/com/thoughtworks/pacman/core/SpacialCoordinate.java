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

    public SpacialCoordinate subtract(SpacialCoordinate spacialCoordinate) {
        return new SpacialCoordinate(x - spacialCoordinate.x, y - spacialCoordinate.y);
    }

    public SpacialCoordinate times(int scale) {
        return new SpacialCoordinate(x * scale, y * scale);
    }

    public int modulo() {
        return (int) Math.sqrt(x * x + y * y);
    }

    public boolean between(SpacialCoordinate c1, SpacialCoordinate c2) {
        return isBetween(x, c1.x, c2.x) && isBetween(y, c1.y, c2.y);
    }

    private boolean isBetween(int target, int bound1, int bound2) {
        return (bound1 <= target && target <= bound2) || (bound2 <= target && target <= bound1);
    }

    public Point toPoint() {
        return new Point(x, y);
    }

    public TileCoordinate toTileCoordinate() {
        return new TileCoordinate(x / Tile.SIZE, y / Tile.SIZE);
    }

    @Override
    public String toString() {
        return String.format("[%2d,%2d]", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SpacialCoordinate spacialCoordinate = (SpacialCoordinate) o;

        if (x != spacialCoordinate.x)
            return false;
        if (y != spacialCoordinate.y)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
