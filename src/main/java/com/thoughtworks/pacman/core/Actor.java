package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.MovementStrategy;

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

    protected void jump(SpacialCoordinate jumpTo) {
        center = jumpTo;
    }

    public void advance(long timeDeltaInMillis) {
        advanceDistance((int) (SPEED * timeDeltaInMillis / 1000));
    }

    private void advanceDistance(int distance) {
        TileCoordinate currentTile = center.toTileCoordinate();
        TileCoordinate nextTile = getMovementStrategy().getNextTile(currentTile);
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
                center = nextTileCenter.remainder(maze);
            } else if (subtract.modulo() < distance) {
                center = nextTileCenter.remainder(maze);
                advanceDistance(distance - subtract.modulo());
            } else {
                SpacialCoordinate movement = subtract.unit().times(distance);
                center = center.add(movement).remainder(maze);
            }
        }
    }

    protected abstract MovementStrategy getMovementStrategy();
}
