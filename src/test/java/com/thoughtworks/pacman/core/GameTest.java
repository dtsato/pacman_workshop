package com.thoughtworks.pacman.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    @Mock
    private Ghosts ghosts;

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

    @Test
    public void advance_shouldDoNothing_whenPacmanIsDead() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        when(pacman.isDead()).thenReturn(true);

        game.advance(10);

        verify(pacman).isDead();
        verifyNoMoreInteractions(pacman, ghosts);
    }

    @Test
    public void advance_shouldFreeGhostsAndAdvanceActors_whenPacmanIsNotDead() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        when(pacman.isDead()).thenReturn(false);

        game.advance(10);

        verify(ghosts).freeGhostsBasedOnScore(0);
        verify(pacman).advance(10);
        verify(ghosts).advance(10);
    }

    @Test
    public void advance_shouldTellPacmanToDie_whenGhostsKillPacman() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        when(pacman.isDead()).thenReturn(false);
        when(ghosts.killed(pacman)).thenReturn(true);

        game.advance(10);

        verify(pacman).die();
    }
}
