package com.thoughtworks.pacman.core.actors;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GhostTest {
    @Test
    public void shouldBeginInStartingPosition() {
        Ghost ghost = new Ghost(null, GhostType.BLINKY);
        assertThat(ghost.getCenter(), equalTo(GhostType.BLINKY.getStartCoordinate()));
    }

    @Test
    public void shouldHaveType() {
        Ghost ghost = new Ghost(null, GhostType.BLINKY);
        assertThat(ghost.getType(), equalTo(GhostType.BLINKY));
    }
}
