package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PacmanTest {
    @Test
    public void shouldBeginInStartingPositionFacingLeft() throws Exception {
        final Pacman pacman = new Pacman(new Maze());
        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(14 * 16, 26 * 16 + 8)));
    }
}
