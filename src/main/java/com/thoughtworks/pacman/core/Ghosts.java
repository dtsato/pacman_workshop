package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.GhostType;
import com.thoughtworks.pacman.core.maze.Maze;

public class Ghosts {
    private Ghost blinky;
    private Ghost pinky;
    private Ghost inky;
    private Ghost clyde;

    public Ghosts(Maze maze) {
        this(new Ghost(maze, GhostType.BLINKY), new Ghost(maze, GhostType.PINKY), new Ghost(maze, GhostType.INKY),
                new Ghost(maze, GhostType.CLYDE));
    }

    Ghosts(Ghost blinky, Ghost pinky, Ghost inky, Ghost clyde) {
        this.blinky = blinky;
        this.pinky = pinky;
        this.inky = inky;
        this.clyde = clyde;
    }

    public Ghost getBlinky() {
        return blinky;
    }

    public Ghost getPinky() {
        return pinky;
    }

    public Ghost getInky() {
        return inky;
    }

    public Ghost getClyde() {
        return clyde;
    }

    public void freeGhostsBasedOnScore(int score) {
        if (blinky.isTrapped()) {
            blinky.free();
        } else if (pinky.isTrapped()) {
            pinky.free();
        } else if (inky.isTrapped() && score > 300) {
            inky.free();
        } else if (clyde.isTrapped() && score > 600) {
            clyde.free();
        }        
    }
}