package com.thoughtworks.pacman.core.tiles;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class WallTest {

    @Test
    public void shouldNotBeMovable() {
        assertFalse(new Wall().isMovable());
    }

    @Test
    public void toString_shouldReturnPlus() {
        assertThat(new Wall().toString(), equalTo("+"));
    }
}
