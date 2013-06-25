package com.thoughtworks.pacman.core.maze;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.Dimension;

import org.junit.Test;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class MazeTest {

    @Test
    public void shouldCalculateDimensionInPixels() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();

        assertThat(maze.getDimension(), equalTo(new Dimension(28 * 16, 36 * 16)));
    }

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
    public void tileAt_shouldReturnEmptyTile_whenTileCoordinateIsInvalidToAllowTeleport() throws Exception {
        String mazeDescription = "+";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.tileAt(new TileCoordinate(2, 2)), instanceOf(EmptyTile.class));
    }

    @Test
    public void getScore_shouldCalculatePointsPerDotsEaten() throws Exception {
        String mazeDescription = "++++\n+..+\n++++";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);

        assertThat(maze.getScore(), equalTo(0));
        Dot dot = (Dot) maze.tileAt(new TileCoordinate(1, 1));
        dot.eat();
        assertThat(maze.getScore(), equalTo(10));
        dot = (Dot) maze.tileAt(new TileCoordinate(2, 1));
        dot.eat();
        assertThat(maze.getScore(), equalTo(20));
    }

    @Test
    public void hasDotsLeft_shouldBeTrue_whenDotsAreLeftUneaten() throws Exception {
        String mazeDescription = "+++\n+.+\n+++";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.hasDotsLeft(), is(true));
    }

    @Test
    public void hasDotsLeft_shouldBeFalse_whenAllDotsAreEaten() throws Exception {
        String mazeDescription = "+++\n+.+\n+++";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);

        Dot dot = (Dot) maze.tileAt(new TileCoordinate(1, 1));
        dot.eat();

        assertThat(maze.hasDotsLeft(), is(false));
    }
}
