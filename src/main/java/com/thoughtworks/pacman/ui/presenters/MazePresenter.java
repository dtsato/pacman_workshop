package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.core.Position;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.pacman.ui.TileToPresenterFactory.toPresenter;

public class MazePresenter implements Presenter {
    private final Maze maze;
    private final List<Presenter> mazeTiles;

    public MazePresenter(Maze maze) {
        this.maze = maze;
        this.mazeTiles = new ArrayList<>();
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                Position position = new Position(x, y);
                Tile tile = maze.tileAt(position);
                mazeTiles.add(toPresenter(tile, position));
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
