package com.thoughtworks.pacman.core;

public class Actor {
    private static final int SPEED = 100;

    private final Maze maze;
    private SpacialCoordinate center;
    private Direction currentDirection;
    private Direction nextDirection;

    public Actor(Maze maze, SpacialCoordinate center, Direction direction) {
        this.maze = maze;
        this.center = center;
        this.currentDirection = direction;
        this.nextDirection = direction;
    }

    public SpacialCoordinate getCenter() {
        return center;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void setNextDirection(Direction direction) {
        this.nextDirection = direction;
    }

    public boolean collidesWith(Actor other) {
        return center.toTileCoordinate().equals(other.center.toTileCoordinate());
    }

    public void advance(long timeDeltaInMillis) {
        TileCoordinate currentTile = center.toTileCoordinate();
        SpacialCoordinate tileCenter = currentTile.toSpacialCoordinate();
        int distance = (int) (SPEED * timeDeltaInMillis / 1000);

        if (canTurnWithin(distance)) {
            distance -= tileCenter.subtract(center).modulo();
            currentDirection = nextDirection;
            center = tileCenter;
        }

        TileCoordinate nextTile = currentTile.add(currentDirection.tileDelta());
        SpacialCoordinate nextCenter = center.add(currentDirection.delta().times(distance));
        if (maze.canMove(nextTile)) {
            center = nextCenter;
        } else {
            center = nextCenter.limitOnDirection(tileCenter, currentDirection);
        }
    }

    private boolean canTurnWithin(int distance) {
        TileCoordinate currentTile = center.toTileCoordinate();
        TileCoordinate turnTile = currentTile.add(nextDirection.tileDelta());
        SpacialCoordinate tileCenter = currentTile.toSpacialCoordinate();
        SpacialCoordinate nextCenter = center.add(currentDirection.delta().times(distance));
        return maze.canMove(turnTile) && tileCenter.between(center, nextCenter);
    }
}
