package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GhostTypeTest {
    @Test
    public void blinky_shouldUseRedImages() {
        assertThat(GhostType.BLINKY.getImageOne(), equalTo("ghost10.jpg"));
        assertThat(GhostType.BLINKY.getImageTwo(), equalTo("ghost11.jpg"));
    }

    @Test
    public void blinky_shouldHaveStartCoordinate() {
        assertThat(GhostType.BLINKY.getStartCoordinate(), equalTo(new SpacialCoordinate(232, 232)));
    }

    @Test
    public void pinky_shouldUseMagentaImages() {
        assertThat(GhostType.PINKY.getImageOne(), equalTo("ghost40.jpg"));
        assertThat(GhostType.PINKY.getImageTwo(), equalTo("ghost41.jpg"));
    }

    @Test
    public void pinky_shouldHaveStartCoordinate() {
        assertThat(GhostType.PINKY.getStartCoordinate(), equalTo(new SpacialCoordinate(232, 280)));
    }

    @Test
    public void inky_shouldUseBlueImages() {
        assertThat(GhostType.INKY.getImageOne(), equalTo("ghost30.jpg"));
        assertThat(GhostType.INKY.getImageTwo(), equalTo("ghost31.jpg"));
    }

    @Test
    public void inky_shouldHaveStartCoordinate() {
        assertThat(GhostType.INKY.getStartCoordinate(), equalTo(new SpacialCoordinate(200, 280)));
    }

    @Test
    public void clyde_shouldUseOrangeImages() {
        assertThat(GhostType.CLYDE.getImageOne(), equalTo("ghost20.jpg"));
        assertThat(GhostType.CLYDE.getImageTwo(), equalTo("ghost21.jpg"));
    }

    @Test
    public void clyde_shouldHaveStartCoordinate() {
        assertThat(GhostType.CLYDE.getStartCoordinate(), equalTo(new SpacialCoordinate(264, 280)));
    }
}
