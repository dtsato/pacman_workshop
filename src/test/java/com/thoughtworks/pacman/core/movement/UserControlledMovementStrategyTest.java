package com.thoughtworks.pacman.core.movement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.thoughtworks.pacman.core.movement.UserControlledMovementStrategy;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class UserControlledMovementStrategyTest {
    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
    }

    @Test
    public void shouldMaintainCurrentDirectionIfNextIsNotSpecified() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        assertThat(movementStrategy.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(13, 26)));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldMaintainCurrentDirectionIfTurnToNextIsImpossible() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.DOWN);
        assertThat(movementStrategy.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(13, 26)));
        assertThat(movementStrategy.isMoving(), is(true));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldTurnDirectionIfPossible() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.RIGHT);
        assertThat(movementStrategy.getNextTile(new TileCoordinate(14, 26)), equalTo(new TileCoordinate(15, 26)));
        assertThat(movementStrategy.isMoving(), is(true));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void shouldStopAndRememberPreviousDirectionIfNextInDesiredDirectionTileIsWall() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        assertThat(movementStrategy.getNextTile(new TileCoordinate(6, 26)), equalTo(new TileCoordinate(6, 26)));
        assertThat(movementStrategy.isMoving(), is(false));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldStopAndRememberPreviousDirectionIfNextInCurrentAndDesiredDirectionTileIsWall() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.UP);
        assertThat(movementStrategy.getNextTile(new TileCoordinate(15, 29)), equalTo(new TileCoordinate(15, 29)));
        assertThat(movementStrategy.isMoving(), is(false));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldGoToTeleportTileOnTheRight() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.RIGHT);
        assertThat(movementStrategy.getNextTile(new TileCoordinate(27, 17)), equalTo(new TileCoordinate(28, 17)));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void shouldGoToTeleportTileOnTheLeft() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.LEFT);
        assertThat(movementStrategy.getNextTile(new TileCoordinate(0, 17)), equalTo(new TileCoordinate(-1, 17)));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void jump_shouldNotBeSupported() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.jump(new TileCoordinate(0, 19));
    }
}
