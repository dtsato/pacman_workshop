package com.thoughtworks.pacman.core;

public class Actor {
    private final Maze maze;
    private SpacialCoordinate centerCoordinate;
    private Direction currentDirection;
    private Direction nextDirection;

    public Actor(Maze maze, SpacialCoordinate centerCoordinate, Direction direction) {
        this.maze = maze;
        this.centerCoordinate = centerCoordinate;
        this.currentDirection = direction;
        this.nextDirection = direction;
    }

    public SpacialCoordinate getCenterCoordinate() {
        return centerCoordinate;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void setNextDirection(Direction direction) {
        this.nextDirection = direction;
    }
}
