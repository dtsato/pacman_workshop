package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.actors.GhostType;
import org.w3c.dom.html.HTMLDocument;

public class Ghost extends Actor {
    private GhostType type;

    public Ghost(Maze maze, GhostType type) {
        super(type.getStartCoordinate(), Direction.DOWN);
        this.type = type;
    }

    @Override
    protected Direction getNextDirection(TileCoordinate tileCoordinate) {
        return null;
    }

    public GhostType getType() {
        return type;
    }
}
