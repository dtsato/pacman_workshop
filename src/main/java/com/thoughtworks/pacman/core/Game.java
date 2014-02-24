package com.thoughtworks.pacman.core;

import java.awt.Dimension;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;

    public Game() throws Exception {
        this(MazeBuilder.buildWalledMaze());
    }

    public Game(Maze maze) {
        this(maze, new Pacman(maze));
    }

    public Game(Maze maze, Pacman pacman) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = new Ghosts(maze);
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
        return ghosts.getBlinky();
    }

    public Ghost getPinky() {
        return ghosts.getPinky();
    }

    public Ghost getInky() {
        return ghosts.getInky();
    }

    public Ghost getClyde() {
        return ghosts.getClyde();
    }

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis);
        ghosts.getBlinky().advance(timeDeltaInMillis);
        ghosts.getPinky().advance(timeDeltaInMillis);
        ghosts.getInky().advance(timeDeltaInMillis);
        ghosts.getClyde().advance(timeDeltaInMillis);

        if (pacman.collidesWith(ghosts.getBlinky()) || pacman.collidesWith(ghosts.getPinky()) || pacman.collidesWith(ghosts.getInky())
                || pacman.collidesWith(ghosts.getClyde())) {
            pacman.die();
        }

        TileCoordinate tileCoordinate = pacman.getCenter().toTileCoordinate();
        Tile pacmanTile = maze.tileAt(tileCoordinate.x, tileCoordinate.y);
        pacmanTile.visit(pacmanTileVisitor);
    }

    public boolean won() {
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        return pacman.isDead();
    }
}
