package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Maze;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MazePresenterTest {
    @Test
    public void shouldCalculateDimensionInPixels() throws Exception {
        Maze maze = new Maze();
        MazePresenter presenter = new MazePresenter(maze);

        assertThat(presenter.getDimension(), equalTo(new Dimension(28 * 16, 36 * 16)));
    }
}
