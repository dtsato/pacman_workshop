package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class MazeTest {

    @Test
    public void toString_shouldReturnPrintableMaze() throws Exception {
        String mazeDescription = "+++\n" + //
                "+.+\n" + //
                "+ +\n" + //
                "+++\n";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.toString(), equalTo(mazeDescription));
    }

}
