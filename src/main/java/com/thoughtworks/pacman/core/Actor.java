package com.thoughtworks.pacman.core;

public class Actor {
    private final Maze maze;
    private Position position;
    private Direction direction;

    public Actor(Maze maze, Position position, Direction direction) {
        this.maze = maze;
        this.position = position;
        this.direction = direction;
    }

    public void move() {
        final Position nextPosition = position.add(direction.delta());
        if (maze.canMove(nextPosition)) {
            this.position = nextPosition;
        }
    }

    public Position getPosition() {
        return position;
    }
}
