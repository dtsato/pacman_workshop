package com.thoughtworks.pacman.ui.presenters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.Dimension;

import org.junit.Test;

import com.thoughtworks.pacman.core.Maze;

public class MazePresenterTest {
    @Test
    public void shouldCalculateDimensionInPixels() throws Exception {
        Maze maze = new Maze();
        MazePresenter presenter = new MazePresenter(maze);

        assertThat(presenter.getDimension(), equalTo(new Dimension(28 * 16, 36 * 16)));
    }
}
