package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class GhostTest {

    private Ghost blinky;
    private Ghost clyde;

    @Before
    public void setUp() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        blinky = new Ghost(maze, GhostType.BLINKY);
        clyde = new Ghost(maze, GhostType.CLYDE);
    }

    @Test
    public void shouldBeginInStartingPosition() {
        assertThat(blinky.getCenter(), equalTo(GhostType.BLINKY.getStartCoordinate()));
    }

    @Test
    public void shouldHaveType() {
        assertThat(blinky.getType(), equalTo(GhostType.BLINKY));
    }

    @Test
    public void isTrapped_shouldBeTrueByDefault() throws Exception {
        assertThat(clyde.isTrapped(), equalTo(true));
    }

    @Test
    public void isTrapped_shouldBeFalseAfterFreed() throws Exception {
        clyde.free();
        assertThat(clyde.isTrapped(), equalTo(false));
    }

    @Test
    public void free_shouldMoveGhostToOutsideOfDoor() throws Exception {
        clyde.free();
        assertThat(clyde.getCenter(), equalTo(GhostType.BLINKY.getStartCoordinate()));
    }

    @Test
    public void isHalted_shouldBeTrueWhenTrapped() throws Exception {
        assertThat(clyde.isHalted(), equalTo(true));
    }

    @Test
    public void isHalted_shouldBeFalseWhenFreed() throws Exception {
        clyde.free();
        assertThat(clyde.isHalted(), equalTo(false));
    }
}
