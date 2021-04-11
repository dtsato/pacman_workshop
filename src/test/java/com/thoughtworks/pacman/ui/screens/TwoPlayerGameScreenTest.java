package com.thoughtworks.pacman.ui.screens;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Ghosts;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwoPlayerGameScreenTest {
    @Mock
    private Graphics2D graphics;
    @Mock
    private KeyEvent keyEvent;
    @Mock
    private GamePresenter gamePresenter;
    @Mock
    private Maze maze;
    @Mock
    private Ghosts ghosts;

    @Test
    public void draw_shouldAdvanceGameWithTimeDelta() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman1 = spy(new Pacman(maze));
        Pacman pacman2 = spy(new Pacman(maze));
        Game game1 = new Game(maze, pacman1);
        Game game2 = new Game(maze, pacman2);
        GamePresenter gamePresenter1 = new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2);
        GamePresenter gamePresenter2 = new GamePresenter(game2);
        TwoPlayerGameScreen twoPlayerGameScreen = new TwoPlayerGameScreen(game1, game2, gamePresenter1, gamePresenter2);

        Thread.sleep(1); // Some time for pacman to move
        twoPlayerGameScreen.draw(graphics);

        verify(pacman1).advance(gt(0L));
        verify(pacman2).advance(gt(0L));
    }

    @Test
    public void nextScreen_shouldReturnGameScreen_whenGameNotOver() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman1 = spy(new Pacman(maze));
        Pacman pacman2 = spy(new Pacman(maze));
        Game game1 = new Game(maze, pacman1);
        Game game2 = new Game(maze, pacman2);
        GamePresenter gamePresenter1 = new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2);
        GamePresenter gamePresenter2 = new GamePresenter(game2);
        TwoPlayerGameScreen twoPlayerGameScreen = new TwoPlayerGameScreen(game1, game2, gamePresenter1, gamePresenter2);

        assertThat(twoPlayerGameScreen.getNextScreen(), instanceOf(TwoPlayerGameScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnMultiplayerEndScreen_whenGameWon() throws Exception {
        Game game1 = new Game(MazeBuilder.buildMaze("+ +"), mock(Pacman.class));
        Game game2 = new Game(MazeBuilder.buildMaze("+ +"), mock(Pacman.class));
        GamePresenter gamePresenter1 = new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2);
        GamePresenter gamePresenter2 = new GamePresenter(game2);
        TwoPlayerGameScreen twoPlayerGameScreen = new TwoPlayerGameScreen(game1, game2, gamePresenter1, gamePresenter2);

        assertThat(twoPlayerGameScreen.getNextScreen(), instanceOf(TwoPlayerGameScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnMultiplayerEndScreen_whenGameLostAndDyingAnimationFinished() throws Exception {
        Game game1 = new Game(MazeBuilder.buildMaze("+ +"), mock(Pacman.class));
        Game game2 = new Game(MazeBuilder.buildMaze("+ +"), mock(Pacman.class));
        GamePresenter gamePresenter1 = new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2);
        GamePresenter gamePresenter2 = new GamePresenter(game2);
        TwoPlayerGameScreen twoPlayerGameScreen = new TwoPlayerGameScreen(game1, game2, gamePresenter1, gamePresenter2);

        game1.getPacman().die();
        game2.getPacman().die();
        when(gamePresenter1.isDying()).thenReturn(false);
        when(gamePresenter2.isDying()).thenReturn(false);

        assertThat(twoPlayerGameScreen.getNextScreen(), instanceOf(TwoPlayerGameScreen.class));
    }

    @Test
    public void keyPressed_shouldMovePacmanLeft() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman1 = spy(new Pacman(maze));
        Pacman pacman2 = spy(new Pacman(maze));
        Game game1 = new Game(maze, pacman1);
        Game game2 = new Game(maze, pacman2);
        GamePresenter gamePresenter1 = new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2);
        GamePresenter gamePresenter2 = new GamePresenter(game2);
        TwoPlayerGameScreen twoPlayerGameScreen = new TwoPlayerGameScreen(game1, game2, gamePresenter1, gamePresenter2);


        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
        twoPlayerGameScreen.keyPressed(keyEvent);

        verify(pacman1).setNextDirection(eq(Direction.LEFT));

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_A);
        twoPlayerGameScreen.keyPressed(keyEvent);

        verify(pacman2).setNextDirection(eq(Direction.LEFT));

    }

    @Test
    public void keyPressed_shouldMovePacmanRight() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman1 = spy(new Pacman(maze));
        Pacman pacman2 = spy(new Pacman(maze));
        Game game1 = new Game(maze, pacman1);
        Game game2 = new Game(maze, pacman2);
        GamePresenter gamePresenter1 = new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2);
        GamePresenter gamePresenter2 = new GamePresenter(game2);
        TwoPlayerGameScreen twoPlayerGameScreen = new TwoPlayerGameScreen(game1, game2, gamePresenter1, gamePresenter2);


        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
        twoPlayerGameScreen.keyPressed(keyEvent);

        verify(pacman1).setNextDirection(eq(Direction.RIGHT));

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_D);
        twoPlayerGameScreen.keyPressed(keyEvent);

        verify(pacman2).setNextDirection(eq(Direction.RIGHT));
    }

    @Test
    public void keyPressed_shouldMovePacmanUp() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman1 = spy(new Pacman(maze));
        Pacman pacman2 = spy(new Pacman(maze));
        Game game1 = new Game(maze, pacman1);
        Game game2 = new Game(maze, pacman2);
        GamePresenter gamePresenter1 = new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2);
        GamePresenter gamePresenter2 = new GamePresenter(game2);
        TwoPlayerGameScreen twoPlayerGameScreen = new TwoPlayerGameScreen(game1, game2, gamePresenter1, gamePresenter2);


        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);
        twoPlayerGameScreen.keyPressed(keyEvent);

        verify(pacman1).setNextDirection(eq(Direction.UP));

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_W);
        twoPlayerGameScreen.keyPressed(keyEvent);

        verify(pacman2).setNextDirection(eq(Direction.UP));
    }

    @Test
    public void keyPressed_shouldMovePacmanDown() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman1 = spy(new Pacman(maze));
        Pacman pacman2 = spy(new Pacman(maze));
        Game game1 = new Game(maze, pacman1);
        Game game2 = new Game(maze, pacman2);
        GamePresenter gamePresenter1 = new GamePresenter(game1, game2.getDimension().width + Tile.SIZE * 2);
        GamePresenter gamePresenter2 = new GamePresenter(game2);
        TwoPlayerGameScreen twoPlayerGameScreen = new TwoPlayerGameScreen(game1, game2, gamePresenter1, gamePresenter2);


        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
        twoPlayerGameScreen.keyPressed(keyEvent);

        verify(pacman1).setNextDirection(eq(Direction.DOWN));

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_S);
        twoPlayerGameScreen.keyPressed(keyEvent);

        verify(pacman2).setNextDirection(eq(Direction.DOWN));
    }
}
