package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.core.Position;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PacmanTest {
    @Test
    public void shouldBeginInStartingPositionFacingLeft() throws Exception {
        final Pacman pacman = new Pacman(new Maze());
        assertThat(pacman.getPosition(), equalTo(new Position(13, 26)));
    }
}
