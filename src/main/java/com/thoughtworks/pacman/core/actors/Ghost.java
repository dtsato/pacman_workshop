package com.thoughtworks.pacman.core.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class Ghost extends Actor {
    private GhostType type;
    private Random random;
    private TileCoordinate previousTile;
    private TileCoordinate desiredTile;

    public Ghost(Maze maze, GhostType type) {
        this(maze, type.getStartCoordinate(), Direction.NONE);
        this.type = type;
    }

    protected Ghost(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, center, direction);
        this.random = new Random();
        this.previousTile = center.toTileCoordinate();
        this.desiredTile = previousTile;
    }

    public GhostType getType() {
        return type;
    }

    @Override
    protected TileCoordinate getNextTile(TileCoordinate currentTile) {
        if (desiredTile.equals(currentTile)) {
            List<TileCoordinate> availableTiles = new ArrayList<>();
            Direction[] validMovements = new Direction[] { Direction.UP, Direction.DOWN, Direction.LEFT,
                    Direction.RIGHT };
            for (Direction direction : validMovements) {
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
