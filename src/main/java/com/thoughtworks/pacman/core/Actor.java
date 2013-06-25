package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.maze.Maze;

public abstract class Actor {
    private static final int SPEED = 100;

    protected final Maze maze;
    private SpacialCoordinate center;

    public Actor(Maze maze, SpacialCoordinate center) {
        this.maze = maze;
        this.center = center;
    }

    public SpacialCoordinate getCenter() {
        return center;
    }

    public boolean collidesWith(Actor other) {
        return center.toTileCoordinate().equals(other.center.toTileCoordinate());
    }

    public void advance(long timeDeltaInMillis) {
        advanceDistance((int) (SPEED * timeDeltaInMillis / 1000));
    }

    private void advanceDistance(int distance) {
        if (distance == 0)
            return;
        TileCoordinate currentTile = center.toTileCoordinate();
        TileCoordinate nextTile = getNextTile(currentTile);
        SpacialCoordinate nextTileCenter = nextTile.toSpacialCoordinate();

        SpacialCoordinate subtract = nextTileCenter.subtract(center);
        if (subtract.isDiagonal()) {
            SpacialCoordinate currentTileCenter = currentTile.toSpacialCoordinate();
            distance = distance - currentTileCenter.subtract(center).modulo();
            subtract = nextTileCenter.subtract(currentTileCenter);
            center = currentTileCenter;
        }

        if (subtract.modulo() > 0) {
            if (subtract.modulo() == distance) {
                center = nextTileCenter;
            } else if (subtract.modulo() < distance) {
                center = nextTileCenter;
                advanceDistance(distance - subtract.modulo());
            } else {
                SpacialCoordinate movement = subtract.unit().times(distance);
                center = center.add(movement);
            }
        }
    }

    protected abstract TileCoordinate getNextTile(TileCoordinate currentTile);
}
