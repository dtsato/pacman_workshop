package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.PacmanMovementStrategy;

public class Pacman extends Actor {
    private boolean dead = false;

    public Pacman(Maze maze) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
    }

    protected Pacman(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, new PacmanMovementStrategy(maze, direction), center);
    }

    public void die() {
        this.dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    protected boolean isHalted() {
        return isDead();
    }

    public void setNextDirection(Direction direction) {
        getPacmanMovementStrategy().setNextDirection(direction);
    }

    public Direction getDirection() {
        return getPacmanMovementStrategy().getDirection();
    }

    public boolean isMoving() {
        return getPacmanMovementStrategy().isMoving();
    }

    private PacmanMovementStrategy getPacmanMovementStrategy() {
        return ((PacmanMovementStrategy)movementStrategy);
    }
}
