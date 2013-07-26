package com.thoughtworks.pacman.core.actors;

import java.util.Random;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.movement.MovementStrategy;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.RandomMovementStrategy;

public class Ghost extends Actor implements MovementStrategy {
    private final RandomMovementStrategy randomMovementStrategy;
    private final GhostType type;
    private boolean free;

    public Ghost(Maze maze, GhostType type) {
        super(maze, type.getStartCoordinate());
        this.type = type;
        this.randomMovementStrategy = new RandomMovementStrategy(getCenter(), maze);
    }

    public GhostType getType() {
        return type;
    }

    public TileCoordinate getNextTile(TileCoordinate currentTile) {
        return randomMovementStrategy.getNextTile(currentTile);
    }

    public boolean isTrapped() {
        return !free;
    }

    public void free() {
        jump(GhostType.doorExit());
        randomMovementStrategy.resetCenter(getCenter());
        free = true;
    }

    @Override
    protected boolean isHalted() {
        return isTrapped();
    }

    protected MovementStrategy getMovementStrategy() {
        return randomMovementStrategy;
    }
}
