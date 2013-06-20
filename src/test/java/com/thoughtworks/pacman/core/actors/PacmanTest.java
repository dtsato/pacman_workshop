package com.thoughtworks.pacman.core.actors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class PacmanTest {
    @Test
    public void shouldBeginInStartingPositionFacingLeft() throws Exception {
        final Pacman pacman = new Pacman(MazeBuilder.buildDefaultMaze());
        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(14 * 16, 26 * 16 + 8)));
    }
}
