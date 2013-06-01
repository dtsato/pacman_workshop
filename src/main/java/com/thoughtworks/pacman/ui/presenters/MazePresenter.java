package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.*;

public class MazePresenter implements Presenter {
    private static final int TILE_SIZE = 16;

    private final Maze maze;

    public MazePresenter(Maze maze) {
        this.maze = maze;
    }

    public Dimension getDimension() {
        return new Dimension(maze.getWidth() * TILE_SIZE, maze.getHeight() * TILE_SIZE);
    }

    @Override
    public void draw(Graphics2D graphics) {

    }
}
