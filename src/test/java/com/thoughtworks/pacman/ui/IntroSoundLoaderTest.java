package com.thoughtworks.pacman.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)

public class IntroSoundLoaderTest{
    private IntroSoundLoader introSoundLoader;

    @Test (expected = ThreadDeath.class)
    public void setUp() throws Exception {
        introSoundLoader = new IntroSoundLoader();
        introSoundLoader.run();
        assertTrue(throwException());
    }


    @Test (expected = Exception.class)
    public void playWonTestThrowsException() throws Exception{
        introSoundLoader = mock(IntroSoundLoader.class);
        introSoundLoader.playIntro();
        assertTrue(throwException());
    }

    private boolean throwException() throws Exception{
        throw new Exception();
    }
}