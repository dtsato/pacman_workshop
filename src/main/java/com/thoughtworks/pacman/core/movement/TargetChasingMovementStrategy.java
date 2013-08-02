package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class TargetChasingMovementStrategy implements MovementStrategy {

    private final Maze maze;
    private final TargetStrategy targetStrategy;
    private TileCoordinate previousTile;
    private TileCoordinate desiredTile;

    public TargetChasingMovementStrategy(Maze maze, TargetStrategy targetStrategy) {
        this.maze = maze;
        this.targetStrategy = targetStrategy;
    }

    public TileCoordinate getNextTile(TileCoordinate currentTile) {
        if (desiredTile == null || desiredTile.equals(currentTile)) {
            TileCoordinate targetTile = targetStrategy.getTarget();

            double minDistance = Double.MAX_VALUE;
            TileCoordinate chosenTile = null;
            for (Direction direction : Direction.validMovements()) {
                TileCoordinate candidateTile = currentTile.add(direction.tileDelta());
                double distance = candidateTile.distanceTo(targetTile);
                if (distance < minDistance && maze.canMove(candidateTile) && !candidateTile.equals(previousTile)) {
                    minDistance = distance;
                    chosenTile = candidateTile;
                }
            }

            previousTile = currentTile;
            desiredTile = chosenTile;
        }
        return desiredTile;
    }

    public void jump(TileCoordinate tileCoordinate) {
    }

    public Direction getDirection() {
        return null;
    }

    public boolean isMoving() {
        return false;
    }
}
