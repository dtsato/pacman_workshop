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
    public void shouldMaintainCurrentDirectionIfNextIsNotSpecified() throws Exception {
        Pacman pacman = new Pacman(maze);
        assertThat(pacman.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(13, 26)));
        assertThat(pacman.isMoving(), is(true));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldMaintainCurrentDirectionIfTurnToNextIsImpossible() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.DOWN);
        assertThat(pacman.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(13, 26)));
        assertThat(pacman.isMoving(), is(true));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldTurnDirectionIfPossible() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.RIGHT);
        assertThat(pacman.isMoving(), is(true));
        assertThat(pacman.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(15, 26)));
        assertThat(pacman.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void shouldStopAndRememberDirectionIfNextInDesiredDirectionTileIsWall() throws Exception {
        Pacman pacman = new Pacman(maze);
        assertThat(pacman.getNextTile(new TileCoordinate(6, 26)), equalTo(new TileCoordinate(6, 26)));
        assertThat(pacman.isMoving(), is(false));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldStopAndRememberDirectionIfNextInCurrentAndDesiredDirectionTileIsWall() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.UP);
        assertThat(pacman.getNextTile(new TileCoordinate(15, 29)), equalTo(new TileCoordinate(15, 29)));
        assertThat(pacman.isMoving(), is(false));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldGoToTeleportTileOnTheRight() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.RIGHT);
        assertThat(pacman.getNextTile(new TileCoordinate(27, 17)), equalTo(new TileCoordinate(28, 17)));
        assertThat(pacman.isMoving(), is(true));
        assertThat(pacman.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void shouldGoToTeleportTileOnTheLeft() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.setNextDirection(Direction.LEFT);
        assertThat(pacman.getNextTile(new TileCoordinate(0, 17)), equalTo(new TileCoordinate(-1, 17)));
        assertThat(pacman.isMoving(), is(true));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldStayPutWhileDying() throws Exception {
        Pacman pacman = new Pacman(maze);
        pacman.die();
        assertThat(pacman.getNextTile(new TileCoordinate(2, 3)), equalTo(new TileCoordinate(2, 3)));
        assertThat(pacman.isMoving(), is(false));
    }

    @Test
    public void isDead_shouldBeFalseByDefault() throws Exception {
        final Pacman pacman = new Pacman(maze);
        assertThat(pacman.isDead(), is(false));
    }
}
