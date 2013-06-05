package com.thoughtworks.pacman.core;

import java.awt.Point;

public class TileCoordinate {
    final int x;
    final int y;

    public TileCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TileCoordinate add(TileCoordinate tileCoordinate) {
        return new TileCoordinate(x + tileCoordinate.x, y + tileCoordinate.y);
    }

    public TileCoordinate times(int scale) {
        return new TileCoordinate(x * scale, y * scale);
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

        TileCoordinate tileCoordinate = (TileCoordinate) o;

        if (x != tileCoordinate.x) return false;
        if (y != tileCoordinate.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
