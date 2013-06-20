package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;
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

    @Test
    public void tileAt_shouldReturnTileAtGivenCoordinate() throws Exception {
        String mazeDescription = "+. ";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.tileAt(new TileCoordinate(0, 0)), instanceOf(Wall.class));
        assertThat(maze.tileAt(new TileCoordinate(1, 0)), instanceOf(Dot.class));
        assertThat(maze.tileAt(new TileCoordinate(2, 0)), instanceOf(EmptyTile.class));
    }

    @Test
    public void tileAt_shouldReturnWall_whenTileCoordinateIsInvalid() throws Exception {
        String mazeDescription = "+";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.tileAt(new TileCoordinate(2, 2)), instanceOf(Wall.class));
    }

}
