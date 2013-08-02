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
    public void getNextTile_shouldDoSomething() {
        // when(targetStrategy.getTarget()).thenReturn(targetTile);
        // assertThat(targetChasingMovementStrategy.getNextTile(currentTile), equalTo(desiredTile));
    }
}
