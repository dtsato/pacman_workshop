package com.thoughtworks.pacman.core.actors;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class GhostTest {
    @Test
    public void shouldBeginInStartingPosition() {
        Ghost ghost = new Ghost(null, GhostType.BLINKY);
        assertThat(ghost.getCenter(), equalTo(GhostType.BLINKY.getStartCoordinate()));
    }

    @Test
    public void shouldHaveType() {
        Ghost ghost = new Ghost(null, GhostType.BLINKY);
        assertThat(ghost.getType(), equalTo(GhostType.BLINKY));
    }

    @Test
    public void shouldChooseNewAllowedDiretionFromAnyPoint() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        SpacialCoordinate center = new SpacialCoordinate(0, 0);
        Ghost ghost = new Ghost(maze, center, Direction.LEFT);

        assertThat(ghost.getNextDirection(new TileCoordinate(9, 14)),
                is(anyOf(equalTo(Direction.RIGHT), equalTo(Direction.DOWN))));
    }

    @Test
    public void nextTile_shouldPickOneAvailableTile() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        SpacialCoordinate center = initialTile.toSpacialCoordinate();
        Ghost ghost = new Ghost(maze, center, null);

        assertThat(ghost.getNextTile(initialTile),
                anyOf(equalTo(new TileCoordinate(14, 14)), equalTo(new TileCoordinate(12, 14))));
    }

    @Test
    public void nextTile_shouldStayWithSameTileIfCurrentTileIsTheSame() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        Ghost ghost = new Ghost(maze, initialTile.toSpacialCoordinate(), null);

        TileCoordinate nextTile = ghost.getNextTile(initialTile);
        assertThat(ghost.getNextTile(initialTile), equalTo(nextTile));
    }

    @Test
    public void nextTile_shouldExcludePreviousTileFromPossibilities() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        TileCoordinate initialTile = new TileCoordinate(18, 4);
        Ghost ghost = new Ghost(maze, initialTile.toSpacialCoordinate(), null);

        TileCoordinate nextTile = ghost.getNextTile(new TileCoordinate(18, 4));
        TileCoordinate direction = nextTile.subtract(initialTile);

        assertThat(ghost.getNextTile(nextTile), equalTo(nextTile.add(direction)));
    }
}
