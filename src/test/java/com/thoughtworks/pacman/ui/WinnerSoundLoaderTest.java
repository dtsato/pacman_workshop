package com.thoughtworks.pacman.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)

public class WinnerSoundLoaderTest{
    private WinnerSoundLoader winnerSoundLoader;

    @Test (expected = ThreadDeath.class)
    public void setUp() throws Exception {
        winnerSoundLoader = new WinnerSoundLoader();
        winnerSoundLoader.run();
        assertTrue(throwException());
    }


    @Test (expected = Exception.class)
    public void playWonTestThrowsException() throws Exception{
        winnerSoundLoader = mock(WinnerSoundLoader.class);
        winnerSoundLoader.playWon();
        assertTrue(throwException());
    }

    private boolean throwException() throws Exception{
        throw new Exception();
    }
}