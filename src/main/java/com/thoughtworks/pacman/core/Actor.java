package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.maze.Maze;

public abstract class Actor {
    private static final int SPEED = 100;

    protected final Maze maze;
    protected SpacialCoordinate center;
    protected Direction currentDirection;

    public Actor(Maze maze, SpacialCoordinate center, Direction direction) {
        this.maze = maze;
        this.center = center;
        this.currentDirection = direction;
    }

    public SpacialCoordinate getCenter() {
        return center;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public boolean collidesWith(Actor other) {
        return center.toTileCoordinate().equals(other.center.toTileCoordinate());
    }

    public void advance(long timeDeltaInMillis) {
        advanceDistance((int) (SPEED * timeDeltaInMillis / 1000));
    }

    private void advanceDistance(int distance) {
        TileCoordinate currentTile = center.toTileCoordinate();
        TileCoordinate nextTile = getNextTile(currentTile);
        if (distance == 0)
            return;
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

    protected TileCoordinate getNextTile(TileCoordinate currentTile) {
        return currentTile;
    }

    protected abstract Direction getNextDirection(TileCoordinate tileCoordinate);
}
