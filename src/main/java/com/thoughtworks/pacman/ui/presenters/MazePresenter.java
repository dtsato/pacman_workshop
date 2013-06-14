package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
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
        for (Tile tile : maze.getTiles()){
            mazeTiles.add(toPresenter(tile));
        }
    }

    public Dimension getDimension() {
        return new Dimension(maze.getWidth() * Tile.SIZE, maze.getHeight() * Tile.SIZE);
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (Presenter tilePresenter : mazeTiles) {
            tilePresenter.draw(graphics);
        }
    }
}
