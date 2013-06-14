package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Wall;

public class MazeBuilderTest {
    @Test
    public void shouldBuildAnEmptyMaze() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        Maze maze = builder.build();

        assertThat(maze.getWidth(), equalTo(0));
        assertThat(maze.getHeight(), equalTo(0));
    }

    @Test
    public void shouldBuildMazeWithOneTile() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("+");
        Maze maze = builder.build();

        assertThat(maze.getWidth(), equalTo(1));
        assertThat(maze.getHeight(), equalTo(1));
        assertThat(maze.tileAt(new TileCoordinate(0, 0)), instanceOf(Wall.class));
    }

    @Test
    public void shouldBuildMazeWithMultipleTilesInSameRow() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("++");
        Maze maze = builder.build();

        assertThat(maze.getWidth(), equalTo(2));
        assertThat(maze.getHeight(), equalTo(1));
        assertThat(maze.tileAt(new TileCoordinate(0, 0)), instanceOf(Wall.class));
        assertThat(maze.tileAt(new TileCoordinate(1, 0)), instanceOf(Wall.class));
    }

    @Test
    public void shouldBuildMazeWithMultipleTilesInSameColumn() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("+");
        builder.process("+");
        Maze maze = builder.build();

        assertThat(maze.getWidth(), equalTo(1));
        assertThat(maze.getHeight(), equalTo(2));
        assertThat(maze.tileAt(new TileCoordinate(0, 0)), instanceOf(Wall.class));
        assertThat(maze.tileAt(new TileCoordinate(0, 1)), instanceOf(Wall.class));
    }
}
