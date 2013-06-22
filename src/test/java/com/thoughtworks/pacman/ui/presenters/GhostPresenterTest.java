package com.thoughtworks.pacman.ui.presenters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.Point;
import java.awt.Rectangle;

import com.thoughtworks.pacman.core.actors.GhostType;
import org.junit.Test;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class GhostPresenterTest {
    @Test
    public void shouldCalculateBoundInPixels() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        GhostPresenter presenter = new GhostPresenter(new Ghost(maze, GhostType.BLINKY));
        Point point = GhostType.BLINKY.getStartCoordinate().toPoint();
        assertThat(presenter.getBounds(), equalTo(new Rectangle(point.x - 10, point.y - 10, 20, 20)));
    }
}
