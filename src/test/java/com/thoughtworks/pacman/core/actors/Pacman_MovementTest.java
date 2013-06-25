package com.thoughtworks.pacman.core.actors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class Pacman_MovementTest {
    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
    }

    @Test
    public void advance_shouldUpdateCenter() throws Exception {
        int initialX = 14 * Tile.SIZE;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Pacman pacman = new Pacman(maze, center, Direction.LEFT);

        pacman.advance(100);

        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(initialX - 10, initialY)));
    }

    @Test
    public void advance_shouldStayPutWhenFacingWall() throws Exception {
        int initialX = 6 * Tile.SIZE + Tile.SIZE / 2;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Pacman pacman = new Pacman(maze, center, Direction.LEFT);

        pacman.advance(100);

        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY)));
    }

    @Test
    public void advance_shouldMoveWhenGoingTowardsWall() throws Exception {
        int initialX = 7 * Tile.SIZE - 1;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Pacman pacman = new Pacman(maze, center, Direction.LEFT);

        pacman.advance(100);

        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(initialX - 7, initialY)));
    }

    @Test
    public void advance_shouldChangeToNextDirectionWhenPossibleBeforeMoving() throws Exception {
        int initialX = 7 * Tile.SIZE - Tile.SIZE / 2;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Pacman pacman = new Pacman(maze, center, Direction.LEFT);

        pacman.setNextDirection(Direction.UP);
        pacman.advance(100);

        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY - 10)));
        assertThat(pacman.getDirection(), equalTo(Direction.UP));
    }

    @Test
    public void advance_shouldKeepGoingInTheCurrentDirectionIfCantTurn() throws Exception {
        int initialX = 8 * Tile.SIZE - Tile.SIZE / 2;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Pacman pacman = new Pacman(maze, center, Direction.LEFT);

        pacman.setNextDirection(Direction.UP);
        pacman.advance(100);

        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(initialX - 10, initialY)));
        assertThat(pacman.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void advance_shouldTurnNearTheCenterOfTheTile() throws Exception {
        int initialX = 7 * Tile.SIZE - Tile.SIZE / 2;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2 - 3;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Pacman pacman = new Pacman(maze, center, Direction.DOWN);

        pacman.setNextDirection(Direction.RIGHT);
        pacman.advance(100);

        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(initialX + 7, initialY + 3)));
        assertThat(pacman.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void advance_shouldTeleport_whenPossible() throws Exception {
        int initialX = 3;
        int initialY = 17 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Pacman pacman = new Pacman(maze, center, Direction.LEFT);

        pacman.advance(100);

        assertThat(pacman.getCenter(), equalTo(new SpacialCoordinate(Tile.SIZE * maze.getWidth() - 7, initialY)));
    }

}
