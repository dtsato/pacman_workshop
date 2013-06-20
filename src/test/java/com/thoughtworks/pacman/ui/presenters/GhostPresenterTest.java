package com.thoughtworks.pacman.ui.presenters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.Color;
import java.awt.Rectangle;

import org.junit.Test;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class GhostPresenterTest {
    @Test
    public void shouldCalculateBoundInPixels() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        GhostPresenter presenter = new GhostPresenter(new Ghost(maze, new SpacialCoordinate(Tile.SIZE / 2, Tile.SIZE / 2)), Color.red);
        assertThat(presenter.getBounds(), equalTo(new Rectangle(0, 0, Tile.SIZE, Tile.SIZE)));
    }
}
