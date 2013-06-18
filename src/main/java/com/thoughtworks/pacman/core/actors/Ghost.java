package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;

public class Ghost extends Actor {
    public Ghost(Maze maze, SpacialCoordinate center) {
        super(maze, center, Direction.DOWN);
    }
}
