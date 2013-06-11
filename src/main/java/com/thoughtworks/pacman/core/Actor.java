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
        int distance = (int) (SPEED * timeDeltaInMillis / 1000);
        center = center.add(currentDirection.delta().times(distance));
    }
}
