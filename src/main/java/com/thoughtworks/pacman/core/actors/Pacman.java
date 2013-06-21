package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class Pacman extends Actor {
    private final Maze maze;
    private Direction currentDirection;
    private Direction desiredDirection;
    private boolean dead = false;

    public Pacman(Maze maze) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2),
                Direction.LEFT);
    }

    protected Pacman(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, center, direction);
        this.maze = maze;
        this.currentDirection = direction;
        this.desiredDirection = direction;
    }

    public void die() {
        this.dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void setNextDirection(Direction direction) {
        this.desiredDirection = direction;
    }

    @Override
    protected Direction getNextDirection(TileCoordinate tileCoordinate) {
        if (allowMove(tileCoordinate, desiredDirection)) {
            currentDirection = desiredDirection;
        } else if (!allowMove(tileCoordinate, currentDirection)) {
            currentDirection = Direction.NONE;
        }
        return currentDirection;
    }

    private boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        return maze.canMove(nextTile);
    }
>>>>>>> Splitting movement logic between Actor and Pacman.
}
