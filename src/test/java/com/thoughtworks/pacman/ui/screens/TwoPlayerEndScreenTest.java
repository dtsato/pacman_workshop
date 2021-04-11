package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Tile;

import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.awt.Graphics2D;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwoPlayerEndScreenTest {
    @Mock
    private Graphics2D graphics;

    @Test
    public void draw_shouldDrawSplashImageAcrossWidth() throws Exception {
        Game game1 = new Game();
        Game game2 = new Game();

        TwoPlayerEndScreen twoPlayerEndScreen = new TwoPlayerEndScreen(game1, game2);
        twoPlayerEndScreen.draw(graphics);

        verify(graphics).drawImage(LostScreen.LOST_SCREEN_IMAGE, 0, 0, 448, 448, null);
        verify(graphics).drawImage(LostScreen.LOST_SCREEN_IMAGE, 448 + Tile.SIZE * 2, 0, 448, 448, null);
    }

    @Test
    public void nextScreen_shouldReturnWinScreen_whenKeyNotPressed() throws Exception {
        Game game1 = new Game();
        Game game2 = new Game();
        TwoPlayerEndScreen twoPlayerEndScreen = new TwoPlayerEndScreen(game1, game2);

        assertThat(twoPlayerEndScreen.getNextScreen(), instanceOf(TwoPlayerEndScreen.class));
    }

    @Test
    public void nextScreen_shouldReturnIntroScreen_whenKeyPressed() throws Exception {
        Game game1 = new Game();
        Game game2 = new Game();
        TwoPlayerEndScreen twoPlayerEndScreen = new TwoPlayerEndScreen(game1, game2);

        twoPlayerEndScreen.keyPressed(null);

        assertThat(twoPlayerEndScreen.getNextScreen(), instanceOf(IntroScreen.class));
    }
}
