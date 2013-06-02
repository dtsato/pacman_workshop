package com.thoughtworks.pacman.core;

public class Actor {
    private final Maze maze;
    private Position position;
    private Direction currentDirection;
    private Direction nextDirection;

    public Actor(Maze maze, Position position, Direction direction) {
        this.maze = maze;
        this.position = position;
        this.currentDirection = direction;
        this.nextDirection = direction;
    }

    public void move() {
        attemptToChangeDirection();
        attemptToChangePosition();
    }

    private void attemptToChangeDirection() {
        Position nextPosition = position.add(nextDirection.delta());
        if (maze.canMove(nextPosition)) {
            this.currentDirection = nextDirection;
        }
    }

    private void attemptToChangePosition() {
        Position nextPosition = position.add(currentDirection.delta());
        if (maze.canMove(nextPosition)) {
            this.position = nextPosition;
        }
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void setNextDirection(Direction direction) {
        this.nextDirection = direction;
    }
}
