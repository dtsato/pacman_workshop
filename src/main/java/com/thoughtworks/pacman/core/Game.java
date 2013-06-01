package com.thoughtworks.pacman.core;

public class Game {
    private final Maze maze;

    public Game() throws Exception {
        this.maze = new Maze();
    }

    public Maze getMaze() {
        return maze;
    }
}
