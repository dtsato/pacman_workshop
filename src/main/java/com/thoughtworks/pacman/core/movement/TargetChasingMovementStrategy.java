package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class TargetChasingMovementStrategy implements MovementStrategy {

    public TargetChasingMovementStrategy(Maze maze, TargetStrategy targetStrategy) {
    }

    public TileCoordinate getNextTile(TileCoordinate currentTile) {
        return null;
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
