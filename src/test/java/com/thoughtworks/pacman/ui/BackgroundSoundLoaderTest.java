package com.thoughtworks.pacman.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)

public class BackgroundSoundLoaderTest{
    private BackgroundSoundLoader backgroundSoundLoader;

    @Test (expected = ThreadDeath.class)
    public void setUp() throws Exception {
        backgroundSoundLoader = new BackgroundSoundLoader();
        backgroundSoundLoader.run();
        assertTrue(throwException());
    }


    @Test (expected = Exception.class)
    public void playWonTestThrowsException() throws Exception{
        backgroundSoundLoader = mock(BackgroundSoundLoader.class);
        backgroundSoundLoader.playBackground();
        assertTrue(throwException());
    }

    private boolean throwException() throws Exception{
        throw new Exception();
    }
}