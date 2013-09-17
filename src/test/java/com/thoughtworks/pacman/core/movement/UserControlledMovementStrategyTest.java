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
        assertThat(movementStrategy.getNextDirection(new TileCoordinate(14, 26)), equalTo(Direction.LEFT));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldMaintainCurrentDirectionIfTurnToNextIsImpossible() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.DOWN);
        assertThat(movementStrategy.getNextDirection(new TileCoordinate(14, 26)), equalTo(Direction.LEFT));
        assertThat(movementStrategy.isMoving(), is(true));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldTurnDirectionIfPossible() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.RIGHT);
        assertThat(movementStrategy.getNextDirection(new TileCoordinate(14, 26)), equalTo(Direction.RIGHT));
        assertThat(movementStrategy.isMoving(), is(true));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void shouldStopAndRememberPreviousDirectionIfNextInDesiredDirectionTileIsWall() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        assertThat(movementStrategy.getNextDirection(new TileCoordinate(6, 26)), equalTo(Direction.NONE));
        assertThat(movementStrategy.isMoving(), is(false));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldStopAndRememberPreviousDirectionIfNextInCurrentAndDesiredDirectionTileIsWall() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.UP);
        assertThat(movementStrategy.getNextDirection(new TileCoordinate(15, 29)), equalTo(Direction.NONE));
        assertThat(movementStrategy.isMoving(), is(false));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void shouldMaintainDirectionAtEdgeOfMazeOnTheRight() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.RIGHT);
        assertThat(movementStrategy.getNextDirection(new TileCoordinate(27, 17)), equalTo(Direction.RIGHT));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void shouldMaintainDirectionAtEdgeOfMazeOnTheLeft() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.setNextDirection(Direction.LEFT);
        assertThat(movementStrategy.getNextDirection(new TileCoordinate(0, 17)), equalTo(Direction.LEFT));
        assertThat(movementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void jump_shouldNotBeSupported() throws Exception {
        UserControlledMovementStrategy movementStrategy = new UserControlledMovementStrategy(maze, Direction.LEFT);
        movementStrategy.jump(new TileCoordinate(0, 19));
    }
}
