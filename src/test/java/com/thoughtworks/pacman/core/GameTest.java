package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameTest {
    @Test
    public void won_shouldBeTrue_whenNoDotsLeftInMaze() throws Exception {
        Maze maze = MazeBuilder.buildMaze("+ +");
        Game game = new Game(maze);

        assertThat(game.won(), is(true));
    }

    @Test
    public void won_shouldBeFalse_whenDotsLeftInMaze() throws Exception {
        Maze maze = MazeBuilder.buildMaze("+.+");
        Game game = new Game(maze);

        assertThat(game.won(), is(false));
    }

    @Test
    public void lost_shouldBeTrue_whenPacmanIsDead() throws Exception {
        Game game = new Game();

        game.getPacman().die();

        assertThat(game.lost(), is(true));
    }

    @Test
    public void lost_shouldBeFalse_whenPacmanIsAlive() throws Exception {
        Game game = new Game();

        assertThat(game.lost(), is(false));
    }
}
