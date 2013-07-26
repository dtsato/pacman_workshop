package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.movement.RandomMovementStrategy;

public class Ghost extends Actor {
    private final GhostType type;
    private boolean free;

    public Ghost(Maze maze, GhostType type) {
        super(maze, new RandomMovementStrategy(type.getStartCoordinate(), maze), type.getStartCoordinate());
        this.type = type;
    }

    public GhostType getType() {
        return type;
    }

    public boolean isTrapped() {
        return !free;
    }

    public void free() {
        jump(GhostType.doorExit());
        free = true;
    }

    @Override
    protected boolean isHalted() {
        return isTrapped();
    }
}
