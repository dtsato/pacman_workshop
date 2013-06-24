package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class Ghost extends Actor {
    private GhostType type;

    public Ghost(Maze maze, GhostType type) {
        super(maze, type.getStartCoordinate(), Direction.DOWN);
        this.type = type;
    }

    public GhostType getType() {
        return type;
    }

    @Override
    protected Direction getNextDirection(TileCoordinate tileCoordinate) {
        TileCoordinate nextTile = tileCoordinate.add(currentDirection.tileDelta());
        if (!maze.canMove(nextTile)) {
            currentDirection = Direction.NONE;
        }
        return currentDirection;
    }
}
