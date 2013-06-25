package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Color;
import java.awt.Graphics2D;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MazePresenterTest {
    @Mock
    private Graphics2D graphics;

    @Test
    public void shouldDrawEachTile() throws Exception {
        String mazeDescription = "+. +";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);

        MazePresenter presenter = new MazePresenter(maze);

        presenter.draw(graphics);

        verify(graphics).setColor(Color.pink); // For dot
        verify(graphics, times(2)).setColor(Color.blue); // For each wall
    }

    @Test
    public void shouldDrawScore() throws Exception {
        String mazeDescription = "+. +";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);

        MazePresenter presenter = new MazePresenter(maze);

        presenter.draw(graphics);

        verify(graphics).drawString(" 0", Tile.SIZE * 5, Tile.SIZE * 2);
    }
}
