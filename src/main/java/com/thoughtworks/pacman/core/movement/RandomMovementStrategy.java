package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMovementStrategy implements MovementStrategy {
    private final Maze maze;
    private final Random random;

    private TileCoordinate previousTile;
    private TileCoordinate desiredTile;
    private Direction desiredDirection;

    public RandomMovementStrategy(SpacialCoordinate center, Maze maze) {
        this(center, maze, new Random());
    }

    protected RandomMovementStrategy(SpacialCoordinate center, Maze maze, Random random) {
        this.maze = maze;
        this.random = random;
        jump(center.toTileCoordinate());
    }

    public void jump(TileCoordinate tileCoordinate) {
        this.previousTile = tileCoordinate;
        this.desiredTile = tileCoordinate;
        this.desiredDirection = Direction.NONE;
    }

    public Direction getDirection() {
        return desiredDirection;
    }

    public boolean isMoving() {
        return true;
    }

    public TileCoordinate getNextTile(TileCoordinate currentTile) {
        if (desiredTile.remainder(maze).equals(currentTile)) {
            List<TileCoordinate> availableTiles = new ArrayList<TileCoordinate>();
            List<Direction> availableDirections = new ArrayList<Direction>();
            for (Direction direction : Direction.validMovements()) {
                TileCoordinate nextTile = currentTile.add(direction.tileDelta());
                if (maze.canMove(nextTile) && !nextTile.equals(previousTile)) {
                    availableDirections.add(direction);
                    availableTiles.add(nextTile);
                }
            }

            int randomIndex = random.nextInt(availableTiles.size());
            desiredTile = availableTiles.get(randomIndex);
            desiredDirection = availableDirections.get(randomIndex);
            previousTile = currentTile;
        }
        return desiredTile;
    }
}