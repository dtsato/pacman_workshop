package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.actors.Pacman;

public class Game {
    private final Maze maze;
    private final Pacman pacman;

    public Game() throws Exception {
        this.maze = new Maze();
        this.pacman = new Pacman(maze);
    }

    public Maze getMaze() {
        return maze;
    }

    public Pacman getPacman() {
        return pacman;
    }
}
