package com.thoughtworks.pacman.core;

public class Actor {
    private final Maze maze;
    private Position centerCoordinate;
    private Direction currentDirection;
    private Direction nextDirection;

    public Actor(Maze maze, Position centerCoordinate, Direction direction) {
        this.maze = maze;
        this.centerCoordinate = centerCoordinate;
        this.currentDirection = direction;
        this.nextDirection = direction;
    }

    public void move() {
        attemptToChangeDirection();
        attemptToChangePosition();
    }

    private void attemptToChangeDirection() {
        Position nextPosition = centerCoordinate.add(nextDirection.delta());
        if (maze.canMove(nextPosition)) {
            this.currentDirection = nextDirection;
        }
    }

    private void attemptToChangePosition() {
        Position nextPosition = centerCoordinate.add(currentDirection.delta());
        if (maze.canMove(nextPosition)) {
            this.centerCoordinate = nextPosition;
        }
    }

    public Position getCenterCoordinate() {
        return centerCoordinate;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void setNextDirection(Direction direction) {
        this.nextDirection = direction;
    }
}
