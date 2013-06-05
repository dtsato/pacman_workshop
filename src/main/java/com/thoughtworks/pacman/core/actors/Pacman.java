package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.core.Position;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;

public class Pacman extends Actor {
    public Pacman(Maze maze) {
        super(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
    }
}
