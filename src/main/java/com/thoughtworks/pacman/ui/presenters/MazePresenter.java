package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.pacman.ui.TileToPresenterFactory.toPresenter;

public class MazePresenter implements Presenter {
    private final Maze maze;
    private final List<Presenter> mazeTiles;

    private static final Font FONT = new Font("Monospaced", Font.BOLD, Tile.SIZE);

    public MazePresenter(Maze maze) {
        this.maze = maze;
        this.mazeTiles = new ArrayList<>();
        for (Tile tile : maze.getTiles()){
            Presenter presenter = toPresenter(tile);
            if (presenter != null) {
                mazeTiles.add(presenter);
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        for (Presenter tilePresenter : mazeTiles) {
            tilePresenter.draw(graphics);
        }
        drawScore(graphics);
    }

    private void drawScore(Graphics2D graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(FONT);
        graphics.drawString(String.format("%2d", maze.getScore()), Tile.SIZE * 5, Tile.SIZE * 2);
    }
}
