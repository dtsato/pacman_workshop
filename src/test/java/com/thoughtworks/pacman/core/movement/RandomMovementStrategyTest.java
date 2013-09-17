package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RandomMovementStrategyTest {
    private Maze maze;
    @Mock
    private Random random;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
        when(random.nextInt(1)).thenReturn(0);
    }

    @Test
    public void nextDirection_shouldPickOneOfTheAvailableDirections() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(anyInt())).thenReturn(0);

        assertThat(randomMovementStrategy.getNextDirection(initialTile), equalTo(Direction.LEFT));
        assertThat(randomMovementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void nextDirection_shouldStayWithSameDirectionIfCurrentTileHasNotChanged() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(anyInt())).thenReturn(0);

        Direction nextDirection = randomMovementStrategy.getNextDirection(initialTile);
        assertThat(randomMovementStrategy.getNextDirection(initialTile), equalTo(nextDirection));
        assertThat(randomMovementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void nextDirection_shouldExcludeOppositeOfCurrentDirectionFromPossibilities() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(18, 4);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(2)).thenReturn(1);
        when(random.nextInt(1)).thenReturn(0);

        Direction nextDirection = randomMovementStrategy.getNextDirection(initialTile);
        assertThat(nextDirection, equalTo(Direction.RIGHT));
        assertThat(randomMovementStrategy.getDirection(), equalTo(Direction.RIGHT));

        assertThat(randomMovementStrategy.getNextDirection(new TileCoordinate(19, 4)), equalTo(Direction.RIGHT));
        assertThat(randomMovementStrategy.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void nextDirection_shouldAllowMovingToEdgeOfMazeOnTheRight() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(27, 17);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(anyInt())).thenReturn(1);

        assertThat(randomMovementStrategy.getNextDirection(initialTile), equalTo(Direction.RIGHT));
        assertThat(randomMovementStrategy.getDirection(), equalTo(Direction.RIGHT));
    }
    
    @Test
    public void nextDirection_shouldContinueAfterTeleportOnTheRight() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(27, 17);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(anyInt())).thenReturn(1);

        assertThat(randomMovementStrategy.getNextDirection(initialTile), equalTo(Direction.RIGHT));
        assertThat(randomMovementStrategy.getNextDirection(new TileCoordinate(0, 17)), equalTo(Direction.RIGHT));
        assertThat(randomMovementStrategy.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void nextDirection_shouldAllowMovingToEdgeOfMazeOnTheLeft() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(0, 17);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(anyInt())).thenReturn(0);

        assertThat(randomMovementStrategy.getNextDirection(initialTile), equalTo(Direction.LEFT));
        assertThat(randomMovementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void nextDirection_shouldContinueAfterTeleportOnTheLeft() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(0, 17);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(anyInt())).thenReturn(0);

        assertThat(randomMovementStrategy.getNextDirection(initialTile), equalTo(Direction.LEFT));
        assertThat(randomMovementStrategy.getNextDirection(new TileCoordinate(27, 17)), equalTo(Direction.LEFT));
        assertThat(randomMovementStrategy.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void isMoving_shouldBeTrue() {
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(
                new TileCoordinate(0, 17).toSpacialCoordinate(), maze, random);
        assertThat(randomMovementStrategy.isMoving(), is(true));
    }
}
