package com.thoughtworks.pacman.core.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.movement.MovementStrategy;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class Ghost extends Actor implements MovementStrategy {
    private GhostType type;
    private Random random;
    private TileCoordinate previousTile;
    private TileCoordinate desiredTile;
    private boolean free;

    public Ghost(Maze maze, GhostType type) {
        this(maze, type.getStartCoordinate(), new Random(), false);
        this.type = type;
    }

    protected Ghost(Maze maze, SpacialCoordinate center, Random random, boolean free) {
        super(maze, center);
        this.random = random;
        this.free = free;
        resetCenter();
    }

    private void resetCenter() {
        this.previousTile = getCenter().toTileCoordinate();
        this.desiredTile = previousTile;
    }

    public GhostType getType() {
        return type;
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

    public boolean isTrapped() {
        return !free;
    }

    public void free() {
        jump(GhostType.doorExit());
        resetCenter();
        free = true;
    }

    @Override
    protected boolean isHalted() {
        return isTrapped();
    }

    protected MovementStrategy getMovementStrategy() {
        return this;
    }
}
