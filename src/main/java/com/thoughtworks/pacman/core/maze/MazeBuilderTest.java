package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class MazeBuilderTest {
    @Test
    public void shouldStartWithAnEmptyMaze() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        Maze maze = builder.build();
        assertThat(maze.getWidth(), equalTo(0));
        assertThat(maze.getHeight(), equalTo(0));
    }
}
