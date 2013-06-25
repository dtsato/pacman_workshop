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
        this(maze, type.getStartCoordinate(), new Random());
        this.type = type;
    }

    protected Ghost(Maze maze, SpacialCoordinate center, Random random) {
        super(maze, center);
        this.random = random;
        this.previousTile = center.toTileCoordinate();
        this.desiredTile = previousTile;
    }

    public GhostType getType() {
        return type;
    }

    @Override
    protected TileCoordinate getNextTile(TileCoordinate currentTile) {
        if (desiredTile.remainder(maze).equals(currentTile)) {
            List<TileCoordinate> availableTiles = new ArrayList<>();
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
