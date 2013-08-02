package com.thoughtworks.pacman.core.movement;

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
        String mazeDescription = "+++++++++++\n" +
                                 "+         +\n" +
                                 "+ +++ +++ +\n" +
                                 "+ + + + + +\n" +
                                 "+ +++ +++ +\n" +
                                 "+         +\n" +
                                 "+ +++ +++ +\n" +
                                 "+ + + + + +\n" +
                                 "+ +++ +++ +\n" +
                                 "+         +\n" +
                                 "+++++++++++\n";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        targetChasingMovementStrategy = new TargetChasingMovementStrategy(maze, targetStrategy);
    }

    @Test
    public void getNextTile_shouldDoSomething() {
        // when(targetStrategy.getTarget()).thenReturn(targetTile);
        // assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(desiredTile));
    }
}
