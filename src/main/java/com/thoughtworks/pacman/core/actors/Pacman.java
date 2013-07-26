package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.MovementStrategy;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;

public class Pacman extends Actor {
    private final PacmanMovementStrategy movementStrategy;
    private boolean dead = false;

    public Pacman(Maze maze) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
    }

    protected Pacman(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, center);
        movementStrategy = new PacmanMovementStrategy(this, maze, direction);
    }

    public void die() {
        this.dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public void setNextDirection(Direction direction) {
        movementStrategy.setNextDirection(direction);
    }

    public Direction getNextDirection() {
        return movementStrategy.getNextDirection();
    }

    public Direction getDirection() {
        return movementStrategy.getDirection();
    }

    public boolean isMoving() {
        return movementStrategy.isMoving();
    }

    protected MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }
}
