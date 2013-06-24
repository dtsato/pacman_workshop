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

    public Ghost(Maze maze, GhostType type) {
        this(maze, type.getStartCoordinate(), Direction.NONE);
        this.type = type;
    }

    protected Ghost(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, center, direction);
        this.random = new Random();
    }

    public GhostType getType() {
        return type;
    }

    @Override
    protected Direction getNextDirection(TileCoordinate tileCoordinate) {
        List<Direction> availableDirections = new ArrayList<>();
        for (Direction direction : currentDirection.validTurns()) {
            addDirectionIfAllowed(availableDirections, tileCoordinate, direction);
        }

        if (currentDirection != Direction.NONE) {
            addDirectionIfAllowed(availableDirections, tileCoordinate, currentDirection);
        }

        return availableDirections.get(random.nextInt(availableDirections.size()));
    }

    private void addDirectionIfAllowed(List<Direction> availableDirections, TileCoordinate tileCoordinate,
            Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        if (maze.canMove(nextTile)) {
            availableDirections.add(direction);
        }
    }
}
