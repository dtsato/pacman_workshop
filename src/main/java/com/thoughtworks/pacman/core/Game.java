package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;
import com.thoughtworks.pacman.core.actors.GhostType;

import java.awt.Dimension;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghost blinky, pinky, inky, clyde;
    private final PacmanTileVisitor pacmanTileVisitor;

    public Game() throws Exception {
        this(MazeBuilder.buildWalledMaze());
    }

    Game(Maze maze) {
        this.maze = maze;
        this.pacman = new Pacman(maze);
        this.blinky = new Ghost(maze, GhostType.BLINKY);
        this.pinky = new Ghost(maze, GhostType.PINKY);
        this.inky = new Ghost(maze, GhostType.INKY);
        this.clyde = new Ghost(maze, GhostType.CLYDE);
        this.pacmanTileVisitor = new PacmanTileVisitor();
    }

    public Maze getMaze() {
        return maze;
    }

    public Dimension getDimension() {
        return maze.getDimension();
    }

    public Pacman getPacman() {
        return pacman;
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

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        if (blinky.isTrapped()) {
            blinky.free();
        } else if (pinky.isTrapped()) {
            pinky.free();
        } else if (inky.isTrapped() && maze.getScore() > 300) {
            inky.free();
        } else if (clyde.isTrapped() && maze.getScore() > 600) {
            clyde.free();
        }

        pacman.advance(timeDeltaInMillis);
        blinky.advance(timeDeltaInMillis);
        pinky.advance(timeDeltaInMillis);
        inky.advance(timeDeltaInMillis);
        clyde.advance(timeDeltaInMillis);

        if (pacman.collidesWith(blinky) || pacman.collidesWith(pinky) || pacman.collidesWith(inky)
                || pacman.collidesWith(clyde)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        pacmanTile.visit(pacmanTileVisitor);
    }

    public boolean won() {
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        return pacman.isDead();
    }
}
