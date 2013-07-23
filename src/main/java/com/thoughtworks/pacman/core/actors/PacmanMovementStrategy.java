package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.MovementStrategy;
import com.thoughtworks.pacman.core.TileCoordinate;

public class PacmanMovementStrategy implements MovementStrategy {
    private final Pacman pacman;
    private Direction desiredDirection;
    private Direction previousDirection;
    private Direction direction;

    public PacmanMovementStrategy(Pacman pacman, Direction direction) {
        this.pacman = pacman;
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
        return direction;
    }

    public Direction getPreviousDirection() {
        return previousDirection;
    }

    @Override
    public TileCoordinate getNextTile(TileCoordinate currentTile) {
        if (pacman.isDead()) {
            previousDirection = direction;
            direction = Direction.NONE;
        }
        else if (pacman.allowMove(currentTile, desiredDirection)) {
            direction = desiredDirection;
        } else if (!pacman.allowMove(currentTile, direction)) {
            previousDirection = direction;
            direction = Direction.NONE;
        }
        return currentTile.add(direction.tileDelta());
    }
}