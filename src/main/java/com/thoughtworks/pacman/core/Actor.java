package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.MovementStrategy;

public abstract class Actor {
    //private static final int SPEED = 100;
    private static int SPEED=100;
    protected final Maze maze;
    protected MovementStrategy movementStrategy;
    private SpacialCoordinate center;

    public Actor(Maze maze, MovementStrategy movementStrategy, SpacialCoordinate center) {
        this.maze = maze;
        this.movementStrategy = movementStrategy;
        this.center = center;
    }

    public SpacialCoordinate getCenter() {
        return center;
    }
    public void setSpeed(int speed){
        SPEED=speed;
    }

    public boolean collidesWith(Actor other) {
        return center.toTileCoordinate().equals(other.center.toTileCoordinate());
    }

    protected void jump(SpacialCoordinate jumpTo) {
        center = jumpTo;
        movementStrategy.jump(center.toTileCoordinate());
    }

    public void advance(long timeDeltaInMillis) {
        if (!isHalted()) {
            advanceDistance((int) (SPEED * timeDeltaInMillis / 1000));
        }
    }

    protected abstract boolean isHalted();

    private void advanceDistance(int distance) {
        TileCoordinate currentTile = center.toTileCoordinate();
        TileCoordinate nextTile = getNextTile(currentTile);
        SpacialCoordinate nextTileCenter = nextTile.toSpacialCoordinate();

        SpacialCoordinate subtract = nextTileCenter.subtract(center);
        if (subtract.isDiagonal()) {
            SpacialCoordinate currentTileCenter = currentTile.toSpacialCoordinate();
            distance = distance - currentTileCenter.subtract(center).magnitude();
            subtract = nextTileCenter.subtract(currentTileCenter);
            center = currentTileCenter;
        }

        if (subtract.magnitude() > 0) {
            if (subtract.magnitude() == distance) {
                center = nextTileCenter.remainder(maze);
            } else if (subtract.magnitude() < distance) {
                center = nextTileCenter.remainder(maze);
                advanceDistance(distance - subtract.magnitude());
            } else {
                SpacialCoordinate movement = subtract.unit().times(distance);
                center = center.add(movement).remainder(maze);
            }
        }
    }

    private TileCoordinate getNextTile(TileCoordinate currentTile) {
        Direction nextDirection = movementStrategy.getNextDirection(currentTile);
        return currentTile.add(nextDirection.tileDelta());
    }
}
