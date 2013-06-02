package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.core.Position;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


public class MazePresenter implements Presenter {
    private final Maze maze;
    private final List<Presenter> mazeTiles;

    public MazePresenter(Maze maze) {
        this.maze = maze;
        this.mazeTiles = new ArrayList<>();

        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                final Position position = new Position(x, y);
                final Tile tile = maze.tileAt(position);
                if (tile instanceof Wall) {
                    this.mazeTiles.add(new WallPresenter((Wall) tile, position));
                } else if (tile instanceof Dot) {
                    this.mazeTiles.add(new DotPresenter((Dot) tile, position));
                }
            }
        }
    }

    public Dimension getDimension() {
        return new Dimension(maze.getWidth() * TILE_SIZE, maze.getHeight() * TILE_SIZE);
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (Presenter tilePresenter : mazeTiles) {
            tilePresenter.draw(graphics);
        }
    }
}
