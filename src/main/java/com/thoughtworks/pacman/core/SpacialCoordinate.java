package com.thoughtworks.pacman.core;

import java.awt.Dimension;
import java.awt.Point;

import com.thoughtworks.pacman.core.maze.Maze;

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

    public SpacialCoordinate remainder(Maze maze) {
        Dimension dimension = maze.getDimension();
        return new SpacialCoordinate((x + dimension.width) % dimension.width, (y + dimension.height) % dimension.height);
    }

    public SpacialCoordinate subtract(SpacialCoordinate spacialCoordinate) {
        return new SpacialCoordinate(x - spacialCoordinate.x, y - spacialCoordinate.y);
    }

    public SpacialCoordinate times(int scale) {
        return new SpacialCoordinate(x * scale, y * scale);
    }

    public int magnitude() {
        return (int) Math.sqrt(x * x + y * y);
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

    public SpacialCoordinate unit() {
        return new SpacialCoordinate(unit(x), unit(y));
    }

    private int unit(int value) {
        return (int) Math.signum(value);
    }

    public boolean isDiagonal() {
        return x != 0 && y != 0;
    }
}
