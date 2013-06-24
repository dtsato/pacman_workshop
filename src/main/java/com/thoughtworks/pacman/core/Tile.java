package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.maze.Maze;

public abstract class Tile {
    public static int SIZE = 16;

    private final TileCoordinate coordinate;
    private Maze maze;

    public Tile(TileCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public SpacialCoordinate getCenter() {
        return coordinate.toSpacialCoordinate();
    }

    public Tile neighbour(Direction direction) {
        TileCoordinate neighbourCoordinate = coordinate.neighbour(direction);
        return maze.tileAt(neighbourCoordinate);
    }

    public abstract boolean isMovable();

    public abstract <T> T visit(TileVisitor<T> visitor);

    public void setMaze(Maze maze) {
        this.maze = maze;
    }
}
