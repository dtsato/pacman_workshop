package com.thoughtworks.pacman.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class MazeTest {

    @Test
    public void shouldBeA28x36Grid() throws Exception {
        final Maze maze = new Maze();
        assertThat(maze.getWidth(), equalTo(28));
        assertThat(maze.getHeight(), equalTo(36));
    }

    @Test
    public void shouldLoadMazeFromFile() throws Exception {
        final Maze maze = new Maze();
        assertThat(maze.tileAt(new TileCoordinate(0, 3)), instanceOf(Wall.class));
        assertThat(maze.tileAt(new TileCoordinate(1, 4)), instanceOf(Dot.class));
        assertThat(maze.tileAt(new TileCoordinate(3, 6)), instanceOf(EmptyTile.class));
    }

    @Test
    public void toString_shouldReturnPrintableMaze() throws Exception {
        final Maze maze = new Maze("simple_maze.map");
        assertThat(maze.toString(), equalTo(
                "+++\n" +
                "+.+\n" +
                "+ +\n" +
                "+++\n"));
    }

}
