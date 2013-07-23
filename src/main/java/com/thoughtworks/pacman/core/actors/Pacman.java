package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.MovementStrategy;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class Pacman extends Actor {
    private PacmanMovementStrategy movementStrategy;
    private boolean dead = false;

    public Pacman(Maze maze) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
    }

    protected Pacman(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, center);
        movementStrategy = new PacmanMovementStrategy(this, direction);
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
        return isMoving() ? movementStrategy.getDirection() : movementStrategy.getPreviousDirection();
    }

    public boolean isMoving() {
        return movementStrategy.getDirection() != Direction.NONE;
    }

    boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        return maze.canMove(nextTile);
    }

    protected MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }
}
