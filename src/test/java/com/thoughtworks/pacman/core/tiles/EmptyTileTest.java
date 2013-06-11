package com.thoughtworks.pacman.core.tiles;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmptyTileTest {

    @Test
    public void shouldBeMovable() {
        assertTrue(new EmptyTile(null).isMovable());
    }

    @Test
    public void toString_shouldReturnSpace() {
        assertThat(new EmptyTile(null).toString(), equalTo(" "));
    }
}
