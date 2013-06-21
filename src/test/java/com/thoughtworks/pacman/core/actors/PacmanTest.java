package com.thoughtworks.pacman.core.actors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE
                + Tile.SIZE / 2)));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldMaintainCurrentDirectionIfNextIsNotSpecified() throws Exception {
        Pacman pacman = new Pacman(maze);
        assertThat(pacman.getNextDirection(new TileCoordinate(14, 26)), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldMaintainCurrentDirectionIfTurnToNextIsImpossible() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.DOWN);
        assertThat(pacman.getNextDirection(new TileCoordinate(14, 26)), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldTurnDirectionIfPossible() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.RIGHT);
        assertThat(pacman.getNextDirection(new TileCoordinate(14, 26)), equalTo(Direction.RIGHT));
    }

    @Test
    public void shouldStopIfNextInDesiredDirectionTileIsWall() throws Exception {
        Pacman pacman = new Pacman(maze);
        assertThat(pacman.getNextDirection(new TileCoordinate(6, 26)), equalTo(Direction.NONE));
    }

    @Test
    public void shouldStopIfNextInCurrentAndDesiredDirectionTileIsWall() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.UP);
        assertThat(pacman.getNextDirection(new TileCoordinate(9, 14)), equalTo(Direction.NONE));
    }

    @Test
    public void isDead_shouldBeFalseByDefault() throws Exception {
        final Pacman pacman = new Pacman(maze);
        assertFalse(pacman.isDead());
    }

    @Test
    public void die_shouldKillPacman() throws Exception {
        final Pacman pacman = new Pacman(maze);

        pacman.die();

        assertTrue(pacman.isDead());
    }
}
