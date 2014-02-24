package com.thoughtworks.pacman.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class GhostsTest {

    private Ghosts ghosts;

    @Before
    public void setUp() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        ghosts = new Ghosts(maze);
    }

    @Test
    public void freeGhostsBasedOnScore_shouldFreeBlinky_whenAllGhostsAreTrapped() throws Exception {
        ghosts.freeGhostsBasedOnScore(0);

        assertThat(ghosts.getBlinky().isTrapped(), is(false));
        assertThat(ghosts.getPinky().isTrapped(), is(true));
        assertThat(ghosts.getInky().isTrapped(), is(true));
        assertThat(ghosts.getClyde().isTrapped(), is(true));
    }

    @Test
    public void freeGhostsBasedOnScore_shouldFreePinky_whenBlinkyIsFree() throws Exception {
        ghosts.getBlinky().free();
        ghosts.freeGhostsBasedOnScore(0);

        assertThat(ghosts.getPinky().isTrapped(), is(false));
        assertThat(ghosts.getInky().isTrapped(), is(true));
        assertThat(ghosts.getClyde().isTrapped(), is(true));
    }

    @Test
    public void freeGhostsBasedOnScore_shouldNotFreeInky_whenBlinkyAndPinkyAreFreeButScoreIs300OrLess() throws Exception {
        ghosts.getBlinky().free();
        ghosts.getPinky().free();
        ghosts.freeGhostsBasedOnScore(300);

        assertThat(ghosts.getInky().isTrapped(), is(true));
        assertThat(ghosts.getClyde().isTrapped(), is(true));
    }

    @Test
    public void freeGhostsBasedOnScore_shouldFreeInky_whenBlinkyAndPinkyAreFreeAndScoreIsMoreThan300() throws Exception {
        ghosts.getBlinky().free();
        ghosts.getPinky().free();
        ghosts.freeGhostsBasedOnScore(301);

        assertThat(ghosts.getInky().isTrapped(), is(false));
        assertThat(ghosts.getClyde().isTrapped(), is(true));
    }

    @Test
    public void freeGhostsBasedOnScore_shouldNotFreeClyde_whenOtherGhostsAreFreeButScoreIs600OrLess() throws Exception {
        ghosts.getBlinky().free();
        ghosts.getPinky().free();
        ghosts.getInky().free();
        ghosts.freeGhostsBasedOnScore(600);

        assertThat(ghosts.getClyde().isTrapped(), is(true));
    }

    @Test
    public void freeGhostsBasedOnScore_shouldFreeClyde_whenOtherGhostsAreFreeAndScoreIsMoreThan600() throws Exception {
        ghosts.getBlinky().free();
        ghosts.getPinky().free();
        ghosts.getInky().free();
        ghosts.freeGhostsBasedOnScore(601);

        assertThat(ghosts.getClyde().isTrapped(), is(false));
    }
}
