package com.thoughtworks.pacman.core.actors;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void isDead_shouldBeFalseByDefault() throws Exception {
        final Pacman pacman = new Pacman(MazeBuilder.buildDefaultMaze());
        assertFalse(pacman.isDead());
    }

    @Test
    public void die_shouldKillPacman() throws Exception {
        final Pacman pacman = new Pacman(MazeBuilder.buildDefaultMaze());

        pacman.die();

        assertTrue(pacman.isDead());
    }

    @Test
    public void eat_shouldIncreaseScore() throws Exception {
        final Pacman pacman = new Pacman(MazeBuilder.buildDefaultMaze());

        assertThat(pacman.getScore(), equalTo(0));
        pacman.eat();
        assertThat(pacman.getScore(), equalTo(10));
        pacman.eat();
        assertThat(pacman.getScore(), equalTo(20));
    }
}
