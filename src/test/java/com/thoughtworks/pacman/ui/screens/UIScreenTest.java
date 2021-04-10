package com.thoughtworks.pacman.ui.screens;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Ghosts;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class UIScreenTest {
    
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
    
    /*
    @Test
    public void draw_shouldAdvanceGameWithTimeDelta() throws Exception {
        Maze maze = MazeBuilder.buildDefaultMaze();
        Pacman pacman = spy(new Pacman(maze));
        Game game = new Game(maze, pacman, ghosts);
        UIScreen uiScreen = new UIScreen(game,gamePresenter);

        Thread.sleep(1); // Some time for pacman to move
        uiScreen.draw(graphics);
        verify(pacman).advance(gt(0L));

    }
    */
    
    @Test
    public void nextScreen_shouldReturnUIScreen_whenResumeAndStartFalse() throws Exception {
        Game game = new Game();
        
        UIScreen uiScreen = new UIScreen(game,gamePresenter);

        assertThat(uiScreen.getNextScreen(), instanceOf(UIScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnGameScreen_whenKeyPressed() throws Exception {
        Game game = new Game();
        UIScreen uiScreen = new UIScreen(game,gamePresenter);

        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_ESCAPE);
        uiScreen.keyPressed(keyEvent);

        assertThat(uiScreen.getNextScreen(), instanceOf(GameScreen.class));

    }




}
