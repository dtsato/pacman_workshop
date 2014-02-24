package com.thoughtworks.pacman.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class GhostsTest {

    private Ghosts ghosts;

    @Before
    public void setUp() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Game game = new Game(maze);
        ghosts = new Ghosts(game);
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

    @Test
    public void advance_shouldAdvanceAllGhosts() throws Exception {
        Ghost g1 = mock(Ghost.class);
        Ghost g2 = mock(Ghost.class);
        Ghost g3 = mock(Ghost.class);
        Ghost g4 = mock(Ghost.class);
        Ghosts ghosts = new Ghosts(g1, g2, g3, g4);

        ghosts.advance(20);

        verify(g1).advance(20);
        verify(g2).advance(20);
        verify(g3).advance(20);
        verify(g4).advance(20);
    }

    @Test
    public void killed_shuldBeTrueWhenBlinkyCollidesWithPacman() throws Exception {
        Pacman pacman = mock(Pacman.class);
        when(pacman.collidesWith(any(Ghost.class))).thenReturn(false);
        when(pacman.collidesWith(ghosts.getBlinky())).thenReturn(true);

        assertThat(ghosts.killed(pacman), is(true));
    }

    @Test
    public void killed_shuldBeTrueWhenPinkyCollidesWithPacman() throws Exception {
        Pacman pacman = mock(Pacman.class);
        when(pacman.collidesWith(any(Ghost.class))).thenReturn(false);
        when(pacman.collidesWith(ghosts.getPinky())).thenReturn(true);

        assertThat(ghosts.killed(pacman), is(true));
    }

    @Test
    public void killed_shuldBeTrueWhenInkyCollidesWithPacman() throws Exception {
        Pacman pacman = mock(Pacman.class);
        when(pacman.collidesWith(any(Ghost.class))).thenReturn(false);
        when(pacman.collidesWith(ghosts.getInky())).thenReturn(true);

        assertThat(ghosts.killed(pacman), is(true));
    }

    @Test
    public void killed_shuldBeTrueWhenClydeCollidesWithPacman() throws Exception {
        Pacman pacman = mock(Pacman.class);
        when(pacman.collidesWith(any(Ghost.class))).thenReturn(false);
        when(pacman.collidesWith(ghosts.getClyde())).thenReturn(true);

        assertThat(ghosts.killed(pacman), is(true));
    }

    @Test
    public void killed_shuldBeFalseWhenNoGhostCollidesWithPacman() throws Exception {
        Pacman pacman = mock(Pacman.class);
        when(pacman.collidesWith(any(Ghost.class))).thenReturn(false);

        assertThat(ghosts.killed(pacman), is(false));
    }
}
