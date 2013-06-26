package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameScreenTest {
    @Mock
    private Graphics2D graphics;
    @Mock
    private KeyEvent keyEvent;
    @Mock
    private GamePresenter gamePresenter;

    @Test
    public void draw_shouldAdvanceGameWithTimeDelta() throws Exception {
        Game game = new Game();
        SpacialCoordinate pacmanCoordinate = game.getPacman().getCenter();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        Thread.sleep(20); // Some time for pacman to move
        gameScreen.draw(graphics);

        assertThat(game.getPacman().getCenter(), not(equalTo(pacmanCoordinate)));
    }

    @Test
    public void nextScreen_shouldReturnGameScreen_whenGameNotOver() throws Exception {
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        assertThat(gameScreen.getNextScreen(), instanceOf(GameScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnWinScreen_whenGameWon() throws Exception {
        Game game = new Game(MazeBuilder.buildMaze("+ +"));
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        assertThat(gameScreen.getNextScreen(), instanceOf(WinScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnLostScreen_whenGameLostAndDyingAnimationFinished() throws Exception {
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        game.getPacman().die();
        when(gamePresenter.isDying()).thenReturn(false);

        assertThat(gameScreen.getNextScreen(), instanceOf(LostScreen.class));
    }

    @Test
    public void keyPressed_shouldMovePacmanLeft() throws Exception {
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
        gameScreen.keyPressed(keyEvent);

        assertThat(game.getPacman().getNextDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void keyPressed_shouldMovePacmanRight() throws Exception {
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
        gameScreen.keyPressed(keyEvent);

        assertThat(game.getPacman().getNextDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void keyPressed_shouldMovePacmanUp() throws Exception {
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);
        gameScreen.keyPressed(keyEvent);

        assertThat(game.getPacman().getNextDirection(), equalTo(Direction.UP));
    }

    @Test
    public void keyPressed_shouldMovePacmanDown() throws Exception {
        Game game = new Game();
        GameScreen gameScreen = new GameScreen(game, gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
        gameScreen.keyPressed(keyEvent);

        assertThat(game.getPacman().getNextDirection(), equalTo(Direction.DOWN));
    }
}
