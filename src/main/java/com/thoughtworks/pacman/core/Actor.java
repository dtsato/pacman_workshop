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

    public void advance(long timeDeltaInMillis) {
        TileCoordinate currentTile = center.toTileCoordinate();
        TileCoordinate nextTile = currentTile.add(currentDirection.tileDelta());

        if (maze.canMove(nextTile)) {
            center = nextCenter(timeDeltaInMillis);
        } else {
            SpacialCoordinate nextCenter = nextCenter(timeDeltaInMillis);
            SpacialCoordinate tileCenter = currentTile.toSpacialCoordinate();
            center = nextCenter.limitOnDirection(tileCenter, currentDirection);
        }
    }

    private SpacialCoordinate nextCenter(long timeDeltaInMillis) {
        int distance = (int) (SPEED * timeDeltaInMillis / 1000);
        return center.add(currentDirection.delta().times(distance));
    }
}
