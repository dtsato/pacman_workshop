package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.core.Position;

public class Pacman extends Actor {
    public Pacman(Maze maze) {
        super(maze, new Position(13, 26), Direction.LEFT);
    }
}
