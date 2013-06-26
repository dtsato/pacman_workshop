package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.thoughtworks.pacman.core.TileCoordinate;
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
        TileCoordinate tileCoordinate = new TileCoordinate(0, 0);
        assertThat(maze.tileAt(tileCoordinate.x, tileCoordinate.y), instanceOf(Wall.class));
    }

    @Test
    public void shouldBuildMazeWithMultipleTilesInSameRow() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("++");
        Maze maze = builder.build();

        assertThat(maze.getWidth(), equalTo(2));
        assertThat(maze.getHeight(), equalTo(1));
        TileCoordinate tileCoordinate1 = new TileCoordinate(0, 0);
        assertThat(maze.tileAt(tileCoordinate1.x, tileCoordinate1.y), instanceOf(Wall.class));
        TileCoordinate tileCoordinate = new TileCoordinate(1, 0);
        assertThat(maze.tileAt(tileCoordinate.x, tileCoordinate.y), instanceOf(Wall.class));
    }

    @Test
    public void shouldBuildMazeWithMultipleTilesInSameColumn() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("+");
        builder.process("+");
        Maze maze = builder.build();

        assertThat(maze.getWidth(), equalTo(1));
        assertThat(maze.getHeight(), equalTo(2));
        TileCoordinate tileCoordinate1 = new TileCoordinate(0, 0);
        assertThat(maze.tileAt(tileCoordinate1.x, tileCoordinate1.y), instanceOf(Wall.class));
        TileCoordinate tileCoordinate = new TileCoordinate(0, 1);
        assertThat(maze.tileAt(tileCoordinate.x, tileCoordinate.y), instanceOf(Wall.class));
    }

    @Test
    public void shouldBuildMazeWithOneDot() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process(".");
        Maze maze = builder.build();

        TileCoordinate tileCoordinate = new TileCoordinate(0, 0);
        assertThat(maze.tileAt(tileCoordinate.x, tileCoordinate.y), instanceOf(Dot.class));
    }

    @Test
    public void shouldBuildMazeWithOneStar() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("*");
        Maze maze = builder.build();

        TileCoordinate tileCoordinate = new TileCoordinate(0, 0);
        assertThat(maze.tileAt(tileCoordinate.x, tileCoordinate.y), instanceOf(Dot.class));
    }

    @Test
    public void shouldBuildMazeWithOneDash() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process("-");
        Maze maze = builder.build();

        TileCoordinate tileCoordinate = new TileCoordinate(0, 0);
        assertThat(maze.tileAt(tileCoordinate.x, tileCoordinate.y), instanceOf(Door.class));
    }

    @Test
    public void shouldBuildMazeWithOneEmptyTile() throws Exception {
        MazeBuilder builder = new MazeBuilder();
        builder.process(" ");
        Maze maze = builder.build();

        TileCoordinate tileCoordinate = new TileCoordinate(0, 0);
        assertThat(maze.tileAt(tileCoordinate.x, tileCoordinate.y), instanceOf(EmptyTile.class));
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
        TileCoordinate tileCoordinate2 = new TileCoordinate(0, 3);
        assertThat(maze.tileAt(tileCoordinate2.x, tileCoordinate2.y), instanceOf(Wall.class));
        TileCoordinate tileCoordinate1 = new TileCoordinate(1, 4);
        assertThat(maze.tileAt(tileCoordinate1.x, tileCoordinate1.y), instanceOf(Dot.class));
        TileCoordinate tileCoordinate = new TileCoordinate(3, 6);
        assertThat(maze.tileAt(tileCoordinate.x, tileCoordinate.y), instanceOf(EmptyTile.class));
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
