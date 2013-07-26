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
        this.desiredTile = previousTile;
    }

    public Direction getDirection() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean isMoving() {
        return true;
    }

    public TileCoordinate getNextTile(TileCoordinate currentTile) {
        if (desiredTile.remainder(maze).equals(currentTile)) {
            List<TileCoordinate> availableTiles = new ArrayList<TileCoordinate>();
            for (Direction direction : Direction.validMovements()) {
                TileCoordinate nextTile = currentTile.add(direction.tileDelta());
                if (maze.canMove(nextTile) && !nextTile.equals(previousTile)) {
                    availableTiles.add(nextTile);
                }
            }

            desiredTile = availableTiles.get(random.nextInt(availableTiles.size()));
            previousTile = currentTile;
        }
        return desiredTile;
    }
}