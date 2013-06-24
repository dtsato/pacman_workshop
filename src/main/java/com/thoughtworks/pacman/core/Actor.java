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
        int distance = (int) (SPEED * timeDeltaInMillis / 1000);
        TileCoordinate currentTile = center.toTileCoordinate();
        SpacialCoordinate nextCenter = center.add(currentDirection.delta().times(distance));
        SpacialCoordinate currentTileCenter = currentTile.toSpacialCoordinate();
        SpacialCoordinate nextTileCenter = nextCenter.toTileCoordinate().toSpacialCoordinate();

        if (currentTileCenter.between(center, nextCenter)) {
            advanceFromCenter(distance, currentTileCenter);
        } else if (nextTileCenter.between(center, nextCenter)) {
            advanceFromCenter(distance, nextTileCenter);
        } else if (maze.canTeleport(currentTile, currentDirection)) {
            center = maze.teleportedCoordinate(currentTile, currentDirection).toSpacialCoordinate();
        } else {
            center = nextCenter;
        }
    }

    private void advanceFromCenter(int distance, SpacialCoordinate currentTileCenter) {
        currentDirection = getNextDirection(currentTileCenter.toTileCoordinate());
        int distanceLeft = distance - currentTileCenter.subtract(center).modulo();
        center = currentTileCenter.add(currentDirection.delta().times(distanceLeft));
    }

    protected abstract Direction getNextDirection(TileCoordinate tileCoordinate);
}
