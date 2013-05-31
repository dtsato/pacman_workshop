package com.thoughtworks.pacman.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DirectionTest {
    @Test
    public void up_delta_shouldDecrementYCoordinate() {
        assertThat(Direction.UP.delta(), equalTo(new Position(0, -1)));
    }

    @Test
    public void down_delta_shouldIncrementYCoordinate() {
        assertThat(Direction.DOWN.delta(), equalTo(new Position(0, 1)));
    }

    @Test
    public void left_delta_shouldDecrementXCoordinate() {
        assertThat(Direction.LEFT.delta(), equalTo(new Position(-1, 0)));
    }

    @Test
    public void right_delta_shouldIncrementXCoordinate() {
        assertThat(Direction.RIGHT.delta(), equalTo(new Position(1, 0)));
    }
}
