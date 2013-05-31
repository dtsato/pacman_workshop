package com.thoughtworks.pacman.core.tiles;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DotTest {

    @Test
    public void shouldBeMovable() {
        assertTrue(new Dot().isMovable());
    }

    @Test
    public void toString_shouldReturnDot() {
        assertThat(new Dot().toString(), equalTo("."));
    }
}
