package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class UserControlledMovementStrategy implements MovementStrategy {
    private final Maze maze;
    private Direction desiredDirection;
    private Direction previousDirection;
    private Direction direction;

    public UserControlledMovementStrategy(Maze maze, Direction startDirection) {
        this.maze = maze;
        this.direction = startDirection;
        this.desiredDirection = startDirection;
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
        if (allowMove(currentTile, desiredDirection)) {
            direction = desiredDirection;
        } else if (!allowMove(currentTile, direction)) {
            previousDirection = direction;
            direction = Direction.NONE;
        }
        return currentTile.add(direction.tileDelta());
    }

    public void jump(TileCoordinate tileCoordinate) {
        throw new UnsupportedOperationException("User can't request to jump");
    }

    private boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        return maze.canMove(nextTile);
    }
}