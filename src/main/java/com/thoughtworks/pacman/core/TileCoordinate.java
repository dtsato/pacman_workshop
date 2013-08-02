package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.maze.Maze;

public class TileCoordinate {
    private final int x;
    private final int y;

    public TileCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SpacialCoordinate toSpacialCoordinate() {
        return new SpacialCoordinate(transformToCenter(x), transformToCenter(y));
    }

    private int transformToCenter(int dimension) {
        return dimension * Tile.SIZE + Tile.SIZE / 2;
    }

    public TileCoordinate add(TileCoordinate delta) {
        return new TileCoordinate(x + delta.x, y + delta.y);
    }

    public TileCoordinate subtract(TileCoordinate delta) {
        return new TileCoordinate(x - delta.x, y - delta.y);
    }

    public double modulo() {
        return Math.sqrt(x * x + y * y);
    }

    public double distanceTo(TileCoordinate tileCoordinate) {
        return subtract(tileCoordinate).modulo();
    }

    public TileCoordinate remainder(Maze maze) {
        return new TileCoordinate((x + maze.getWidth()) % maze.getWidth(), (y + maze.getHeight()) % maze.getHeight());
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
