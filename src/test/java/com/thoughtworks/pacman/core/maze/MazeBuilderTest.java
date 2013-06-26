package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
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
        assertThat(maze.tileAt(0, 0), instanceOf(Wall.class));
    }

    @Test
    public void shouldBuildMazeWithMultipleTilesInSameRow() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("++");
        Maze maze = builder.build();

        assertThat(maze.getWidth(), equalTo(2));
        assertThat(maze.getHeight(), equalTo(1));
        assertThat(maze.tileAt(0, 0), instanceOf(Wall.class));
        assertThat(maze.tileAt(1, 0), instanceOf(Wall.class));
    }

    @Test
    public void shouldBuildMazeWithMultipleTilesInSameColumn() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("+");
        builder.process("+");
        Maze maze = builder.build();

        assertThat(maze.getWidth(), equalTo(1));
        assertThat(maze.getHeight(), equalTo(2));
        assertThat(maze.tileAt(0, 0), instanceOf(Wall.class));
        assertThat(maze.tileAt(0, 1), instanceOf(Wall.class));
    }

    @Test
    public void shouldBuildMazeWithOneDot() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process(".");
        Maze maze = builder.build();

        assertThat(maze.tileAt(0, 0), instanceOf(Dot.class));
    }

    @Test
    public void shouldBuildMazeWithOneStar() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("*");
        Maze maze = builder.build();

        assertThat(maze.tileAt(0, 0), instanceOf(Dot.class));
    }

    @Test
    public void shouldBuildMazeWithOneDash() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("-");
        Maze maze = builder.build();

        assertThat(maze.tileAt(0, 0), instanceOf(Door.class));
    }

    @Test
    public void shouldBuildMazeWithOneEmptyTile() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process(" ");
        Maze maze = builder.build();

        assertThat(maze.tileAt(0, 0), instanceOf(EmptyTile.class));
    }

    @Test
    public void defaultMazeShouldBeA28x36Grid() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        assertThat(maze.getWidth(), equalTo(28));
        assertThat(maze.getHeight(), equalTo(36));
    }

    @Test
    public void defaultMazeShouldBeLoadedFromFile() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        assertThat(maze.tileAt(0, 3), instanceOf(Wall.class));
        assertThat(maze.tileAt(1, 4), instanceOf(Dot.class));
        assertThat(maze.tileAt(3, 6), instanceOf(EmptyTile.class));
    }

    @Test
    public void shouldBuildMazeFromString() throws Exception {
        Maze maze = MazeBuilder.buildMaze(
                "+++\n" +
                "+.+\n" +
                "+ +\n" +
                "+++\n");
        assertThat(maze.getWidth(), equalTo(3));
        assertThat(maze.getHeight(), equalTo(4));
    }

}
