package com.thoughtworks.pacman.core.movement;

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
import static org.junit.Assert.assertThat;
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
    public void nextTile_shouldPickOneAvailableTile() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(2)).thenReturn(0);

        assertThat(randomMovementStrategy.getNextTile(initialTile), equalTo(new TileCoordinate(12, 14)));
    }

    @Test
    public void nextTile_shouldStayWithSameTileIfCurrentTileIsTheSame() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(13, 14);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(2)).thenReturn(0);

        TileCoordinate nextTile = randomMovementStrategy.getNextTile(initialTile);
        assertThat(randomMovementStrategy.getNextTile(initialTile), equalTo(nextTile));
    }

    @Test
    public void nextTile_shouldExcludePreviousTileFromPossibilities() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(18, 4);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(2)).thenReturn(1);

        TileCoordinate nextTile = randomMovementStrategy.getNextTile(new TileCoordinate(18, 4));
        assertThat(nextTile, equalTo(new TileCoordinate(19, 4)));

        assertThat(randomMovementStrategy.getNextTile(nextTile), equalTo(new TileCoordinate(20, 4)));
    }

    @Test
    public void nextTile_shouldGoToTeleportTileOnTheRight() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(27, 17);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(2)).thenReturn(1);

        assertThat(randomMovementStrategy.getNextTile(initialTile), equalTo(new TileCoordinate(28, 17)));
    }

    @Test
    public void nextTile_shouldContinueAfterTeleportOnTheRight() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(27, 17);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(2)).thenReturn(1);

        assertThat(randomMovementStrategy.getNextTile(initialTile), equalTo(new TileCoordinate(28, 17)));
        assertThat(randomMovementStrategy.getNextTile(new TileCoordinate(0, 17)), equalTo(new TileCoordinate(1, 17)));
    }

    @Test
    public void nextTile_shouldGoToTeleportTileOnTheLeft() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(0, 17);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(2)).thenReturn(0);

        assertThat(randomMovementStrategy.getNextTile(initialTile), equalTo(new TileCoordinate(-1, 17)));
    }

    @Test
    public void nextTile_shouldContinueAfterTeleportOnTheLeft() throws Exception {
        TileCoordinate initialTile = new TileCoordinate(0, 17);
        RandomMovementStrategy randomMovementStrategy = new RandomMovementStrategy(initialTile.toSpacialCoordinate(), maze, random);

        when(random.nextInt(2)).thenReturn(0);

        assertThat(randomMovementStrategy.getNextTile(initialTile), equalTo(new TileCoordinate(-1, 17)));
        assertThat(randomMovementStrategy.getNextTile(new TileCoordinate(27, 17)), equalTo(new TileCoordinate(26, 17)));
    }
}
