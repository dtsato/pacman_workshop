package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;

public class PacmanMovementStrategy implements MovementStrategy {
    private final Pacman pacman;
    private final Maze maze;
    private Direction desiredDirection;
    private Direction previousDirection;
    private Direction direction;

    public PacmanMovementStrategy(Pacman pacman, Maze maze, Direction direction) {
        this.pacman = pacman;
        this.maze = maze;
        this.direction = direction;
        this.desiredDirection = direction;
    }

    public Direction getNextDirection() {
        return desiredDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.desiredDirection = nextDirection;
    }

    public Direction getDirection() {
        return isMoving() ? direction : previousDirection;
    }

    public boolean isMoving() {
        return direction != Direction.NONE;
    }

    public TileCoordinate getNextTile(TileCoordinate currentTile) {
        if (pacman.isDead()) {
            previousDirection = direction;
            direction = Direction.NONE;
        }
        else if (allowMove(currentTile, desiredDirection)) {
            direction = desiredDirection;
        } else if (!allowMove(currentTile, direction)) {
            previousDirection = direction;
            direction = Direction.NONE;
        }
        return currentTile.add(direction.tileDelta());
    }

    private boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        return maze.canMove(nextTile);
    }
}