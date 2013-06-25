package com.thoughtworks.pacman.core.actors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class GhostTest {
    private Maze maze;
    private Random random;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
        random = mock(Random.class);
        when(random.nextInt(1)).thenReturn(0);
    }

    @Test
    public void shouldBeginInStartingPosition() {
        Ghost ghost = new Ghost(maze, GhostType.BLINKY);
        assertThat(ghost.getCenter(), equalTo(GhostType.BLINKY.getStartCoordinate()));
    }

    @Test
    public void shouldHaveType() {
        Ghost ghost = new Ghost(maze, GhostType.BLINKY);
        assertThat(ghost.getType(), equalTo(GhostType.BLINKY));
    }

    @Test
    public void shouldStartTrapped() throws Exception {
        Ghost ghost = new Ghost(maze, GhostType.CLYDE);
        assertThat(ghost.isTrapped(), equalTo(true));
    }

    @Test
    public void shouldNotBeTrappedAfterFreed() throws Exception {
        Ghost ghost = new Ghost(maze, GhostType.CLYDE);
        ghost.free();
        assertThat(ghost.isTrapped(), equalTo(false));
    }

    @Test
    public void free_shouldMoveGhostToOutsideOfDoor() throws Exception {
        Ghost ghost = new Ghost(maze, GhostType.CLYDE);
        ghost.free();
        assertThat(ghost.getCenter(), equalTo(GhostType.BLINKY.getStartCoordinate()));
    }

    @Test
    public void nextTile_shouldNotMoveIfNotFree() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        SpacialCoordinate center = initialTile.toSpacialCoordinate();
        Ghost ghost = new Ghost(maze, center, random, false);

        assertThat(ghost.getNextTile(initialTile), equalTo(initialTile));
    }

    @Test
    public void nextTile_shouldPickOneAvailableTile() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        SpacialCoordinate center = initialTile.toSpacialCoordinate();
        Ghost ghost = new Ghost(maze, center, random, true);

        when(random.nextInt(2)).thenReturn(0);

        assertThat(ghost.getNextTile(initialTile), equalTo(new TileCoordinate(12, 14)));
    }

    @Test
    public void nextTile_shouldStayWithSameTileIfCurrentTileIsTheSame() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        Ghost ghost = new Ghost(maze, initialTile.toSpacialCoordinate(), random, true);

        when(random.nextInt(2)).thenReturn(0);

        TileCoordinate nextTile = ghost.getNextTile(initialTile);
        assertThat(ghost.getNextTile(initialTile), equalTo(nextTile));
    }

    @Test
    public void nextTile_shouldExcludePreviousTileFromPossibilities() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(18, 4);
        Ghost ghost = new Ghost(maze, initialTile.toSpacialCoordinate(), random, true);

        when(random.nextInt(2)).thenReturn(1);

        TileCoordinate nextTile = ghost.getNextTile(new TileCoordinate(18, 4));
        assertThat(nextTile, equalTo(new TileCoordinate(19, 4)));

        assertThat(ghost.getNextTile(nextTile), equalTo(new TileCoordinate(20, 4)));
    }

    @Test
    public void nextTile_shouldGoToTeleportTileOnTheRight() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(27, 17);
        Ghost ghost = new Ghost(maze, initialTile.toSpacialCoordinate(), random, true);

        when(random.nextInt(2)).thenReturn(1);

        assertThat(ghost.getNextTile(initialTile), equalTo(new TileCoordinate(28, 17)));
    }

    @Test
    public void nextTile_shouldContinueAfterTeleportOnTheRight() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(27, 17);
        Ghost ghost = new Ghost(maze, initialTile.toSpacialCoordinate(), random, true);

        when(random.nextInt(2)).thenReturn(1);

        assertThat(ghost.getNextTile(initialTile), equalTo(new TileCoordinate(28, 17)));
        assertThat(ghost.getNextTile(new TileCoordinate(0, 17)), equalTo(new TileCoordinate(1, 17)));
    }

    @Test
    public void nextTile_shouldGoToTeleportTileOnTheLeft() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(0, 17);
        Ghost ghost = new Ghost(maze, initialTile.toSpacialCoordinate(), random, true);

        when(random.nextInt(2)).thenReturn(0);

        assertThat(ghost.getNextTile(initialTile), equalTo(new TileCoordinate(-1, 17)));
    }

    @Test
    public void nextTile_shouldContinueAfterTeleportOnTheLeft() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(0, 17);
        Ghost ghost = new Ghost(maze, initialTile.toSpacialCoordinate(), random, true);

        when(random.nextInt(2)).thenReturn(0);

        assertThat(ghost.getNextTile(initialTile), equalTo(new TileCoordinate(-1, 17)));
        assertThat(ghost.getNextTile(new TileCoordinate(27, 17)), equalTo(new TileCoordinate(26, 17)));
    }
}
