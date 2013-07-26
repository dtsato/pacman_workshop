package com.thoughtworks.pacman.core.actors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class PacmanTest {
    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
    }

    @Test
    public void shouldBeginInStartingPositionFacingLeft() throws Exception {
        final Pacman pacman = new Pacman(maze);
        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2)));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void isDead_shouldBeFalseByDefault() throws Exception {
        final Pacman pacman = new Pacman(maze);
        assertThat(pacman.isDead(), is(false));
    }

    @Test
    public void isDead_shouldBeTrueAfterDead() throws Exception {
        final Pacman pacman = new Pacman(maze);
        pacman.die();
        assertThat(pacman.isDead(), is(true));
    }
}
