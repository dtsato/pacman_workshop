package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.Dot;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghost blinky, pinky, inky, clyde;
    private final PacmanTileVisitor pacmanTileVisitor;

    public Game() throws Exception {
        this.maze = MazeBuilder.buildDefaultMaze();
        this.pacman = new Pacman(maze);
        this.blinky = new Ghost(maze, new SpacialCoordinate(14 * Tile.SIZE, 14 * Tile.SIZE + Tile.SIZE / 2));
        this.pinky = new Ghost(maze, new SpacialCoordinate(14 * Tile.SIZE, 17 * Tile.SIZE + Tile.SIZE / 2));
        this.inky = new Ghost(maze, new SpacialCoordinate(12 * Tile.SIZE, 17 * Tile.SIZE + Tile.SIZE / 2));
        this.clyde = new Ghost(maze, new SpacialCoordinate(16 * Tile.SIZE, 17 * Tile.SIZE + Tile.SIZE / 2));
        this.pacmanTileVisitor = new PacmanTileVisitor(pacman);
    }

    public Maze getMaze() {
        return maze;
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

        pacman.advance(timeDeltaInMillis);
        blinky.advance(timeDeltaInMillis);
        pinky.advance(timeDeltaInMillis);
        inky.advance(timeDeltaInMillis);
        clyde.advance(timeDeltaInMillis);

        if (pacman.collidesWith(blinky) || pacman.collidesWith(pinky) || pacman.collidesWith(inky) || pacman.collidesWith(clyde)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        pacmanTile.visit(pacmanTileVisitor);
	}
}
