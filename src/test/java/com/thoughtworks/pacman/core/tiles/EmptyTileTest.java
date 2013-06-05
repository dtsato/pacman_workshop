package com.thoughtworks.pacman.core.tiles;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class EmptyTileTest {

    @Test
    public void shouldNotBeMovable() {
        assertFalse(new EmptyTile(null).isMovable());
    }

    @Test
    public void toString_shouldReturnSpace() {
        assertThat(new EmptyTile(null).toString(), equalTo(" "));
    }
}
