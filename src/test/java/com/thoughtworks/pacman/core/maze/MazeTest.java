package com.thoughtworks.pacman.core.maze;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
    public void teleportedCoordinate_shouldReturnCoordinateAtTop_whenDirectionIsUp() throws Exception {
        String mazeDescription = "+ +\n   \n+ +";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.teleportedCoordinate(new TileCoordinate(1, -1), Direction.UP), equalTo(new TileCoordinate(1, 2)));
    }

    @Test
    public void teleportedCoordinate_shouldReturnCoordinateAtBottom_whenDirectionIsDown() throws Exception {
        String mazeDescription = "+ +\n   \n+ +";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.teleportedCoordinate(new TileCoordinate(1, 3), Direction.DOWN), equalTo(new TileCoordinate(1, 0)));
    }

    @Test
    public void teleportedCoordinate_shouldReturnCoordinateAtLeft_whenDirectionIsRight() throws Exception {
        String mazeDescription = "+ +\n   \n+ +";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.teleportedCoordinate(new TileCoordinate(3, 1), Direction.RIGHT), equalTo(new TileCoordinate(0, 1)));
    }

    @Test
    public void teleportedCoordinate_shouldReturnCoordinateAtRight_whenDirectionIsLeft() throws Exception {
        String mazeDescription = "+ +\n   \n+ +";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertThat(maze.teleportedCoordinate(new TileCoordinate(-1, 1), Direction.LEFT), equalTo(new TileCoordinate(2, 1)));
    }

    @Test
    public void canTeleport_shouldBeTrue_whenTileIsInvalidAndOppositeTileIsMovable() throws Exception {
        String mazeDescription = "+ +\n   \n+ +";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertTrue(maze.canTeleport(new TileCoordinate(-1, 1), Direction.LEFT));
    }

    @Test
    public void canTeleport_shouldBeFalse_whenTileIsInvalidAndOppositeTileIsNotMovable() throws Exception {
        String mazeDescription = "+ +\n  +\n+ +";
        Maze maze = MazeBuilder.buildMaze(mazeDescription);
        assertFalse(maze.canTeleport(new TileCoordinate(-1, 1), Direction.LEFT));
    }
}
