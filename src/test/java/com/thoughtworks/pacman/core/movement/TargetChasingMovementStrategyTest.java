package com.thoughtworks.pacman.core.movement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TargetChasingMovementStrategyTest {

    private TargetChasingMovementStrategy targetChasingMovementStrategy;
    @Mock
    private TargetStrategy targetStrategy;

    @Before
    public void setUp() throws Exception {
        //                     x= 0123456789X      // y
        String mazeDescription = "+++++++++++\n" + // 0
                                 "+         +\n" + // 1
                                 "+ +++ +++ +\n" + // 2
                                 "+ + + + + +\n" + // 3
                                 "+ +++ +++ +\n" + // 4
                                 "+         +\n" + // 5
                                 "+ +++ +++ +\n" + // 6
                                 "+ + + + + +\n" + // 7
                                 "+ +++ +++ +\n" + // 8
                                 "+         +\n" + // 9
                                 "+++++++++++\n";  // 10
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        targetChasingMovementStrategy = new TargetChasingMovementStrategy(maze, targetStrategy);
    }

    @Test
    public void getNextTile_shouldGoRight_whenTargetIsImmediatelyToTheRight() {
        TileCoordinate currentTile = new TileCoordinate(5, 5);
        TileCoordinate targetTile = new TileCoordinate(6, 5);
        when(targetStrategy.getTarget()).thenReturn(targetTile);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(targetTile));
    }

    @Test
    public void getNextTile_shouldGoRight_whenTargetIsFarToTheRight() {
        TileCoordinate currentTile = new TileCoordinate(5, 5);
        TileCoordinate targetTile = new TileCoordinate(7, 5);
        when(targetStrategy.getTarget()).thenReturn(targetTile);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(new TileCoordinate(6, 5)));
    }

    @Test
    public void getNextTile_shouldGoLeft_whenTargetIsFarToTheLeft() {
        TileCoordinate currentTile = new TileCoordinate(5, 5);
        TileCoordinate targetTile = new TileCoordinate(3, 5);
        when(targetStrategy.getTarget()).thenReturn(targetTile);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(new TileCoordinate(4, 5)));
    }

    @Test
    public void getNextTile_shouldGoUp_whenTargetIsFarAbove() {
        TileCoordinate currentTile = new TileCoordinate(5, 5);
        TileCoordinate targetTile = new TileCoordinate(5, 3);
        when(targetStrategy.getTarget()).thenReturn(targetTile);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(new TileCoordinate(5, 4)));
    }

    @Test
    public void getNextTile_shouldNotGoUp_whenTopTileIsWall() {
        TileCoordinate currentTile = new TileCoordinate(3, 5);
        TileCoordinate targetTile = new TileCoordinate(4, 2);
        when(targetStrategy.getTarget()).thenReturn(targetTile);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(new TileCoordinate(4, 5)));
    }

    @Test
    public void getNextTile_shouldChooseUp_whenUpAndLeftAreSameDistance() {
        TileCoordinate currentTile = new TileCoordinate(5, 5);
        TileCoordinate targetTile = new TileCoordinate(4, 4);
        when(targetStrategy.getTarget()).thenReturn(targetTile);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(new TileCoordinate(5, 4)));
    }

    @Test
    public void getNextTile_shouldChooseLeft_whenLeftAndDownAreSameDistance() {
        TileCoordinate currentTile = new TileCoordinate(5, 5);
        TileCoordinate targetTile = new TileCoordinate(4, 6);
        when(targetStrategy.getTarget()).thenReturn(targetTile);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(new TileCoordinate(4, 5)));
    }

    @Test
    public void getNextTile_shouldNotReverseDirection_whenPacmanIsInOppositeDirection() {
        TileCoordinate currentTile1 = new TileCoordinate(2, 1);
        TileCoordinate targetTile1 = new TileCoordinate(5, 1);

        TileCoordinate currentTile2 = new TileCoordinate(3, 1);
        TileCoordinate targetTile2 = new TileCoordinate(1, 1);
        when(targetStrategy.getTarget()).thenReturn(targetTile1, targetTile2);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile1), equalTo(currentTile2));
        assertThat(targetChasingMovementStrategy.getNextTile(currentTile2), equalTo(new TileCoordinate(4, 1)));
    }

    @Test
    public void getNextTile_shouldNotReverseDirection_whileMovingInSameTile() {
        TileCoordinate currentTile = new TileCoordinate(2, 1);
        TileCoordinate targetTile1 = new TileCoordinate(5, 1);
        TileCoordinate targetTile2 = new TileCoordinate(1, 1);
        when(targetStrategy.getTarget()).thenReturn(targetTile1, targetTile2);

        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(new TileCoordinate(3, 1)));
        assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(new TileCoordinate(3, 1)));
    }
}
