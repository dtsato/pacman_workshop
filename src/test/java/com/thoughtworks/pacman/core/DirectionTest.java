package com.thoughtworks.pacman.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DirectionTest {
    @Test
    public void up_delta_shouldDecrementYCoordinate() {
        assertThat(Direction.UP.delta(), equalTo(new SpacialCoordinate(0, -1)));
    }

    @Test
    public void up_tileDelta_shouldDecrementYCoordinate() {
        assertThat(Direction.UP.tileDelta(), equalTo(new TileCoordinate(0, -1)));
    }

    @Test
    public void up_startAngle_shouldFaceUp() {
        assertThat(Direction.UP.getStartAngle(), equalTo(130));
    }

    @Test
    public void down_delta_shouldIncrementYCoordinate() {
        assertThat(Direction.DOWN.delta(), equalTo(new SpacialCoordinate(0, 1)));
    }

    @Test
    public void down_tileDelta_shouldIncrementYCoordinate() {
        assertThat(Direction.DOWN.tileDelta(), equalTo(new TileCoordinate(0, 1)));
    }

    @Test
    public void down_startAngle_shouldFaceDown() {
        assertThat(Direction.DOWN.getStartAngle(), equalTo(310));
    }

    @Test
    public void left_delta_shouldDecrementXCoordinate() {
        assertThat(Direction.LEFT.delta(), equalTo(new SpacialCoordinate(-1, 0)));
    }

    @Test
    public void left_tileDelta_shouldDecrementXCoordinate() {
        assertThat(Direction.LEFT.tileDelta(), equalTo(new TileCoordinate(-1, 0)));
    }

    @Test
    public void left_startAngle_shouldFaceLeft() {
        assertThat(Direction.LEFT.getStartAngle(), equalTo(220));
    }

    @Test
    public void right_delta_shouldIncrementXCoordinate() {
        assertThat(Direction.RIGHT.delta(), equalTo(new SpacialCoordinate(1, 0)));
    }

    @Test
    public void right_tileDelta_shouldIncrementXCoordinate() {
        assertThat(Direction.RIGHT.tileDelta(), equalTo(new TileCoordinate(1, 0)));
    }

    @Test
    public void right_startAngle_shouldFaceRight() {
        assertThat(Direction.RIGHT.getStartAngle(), equalTo(40));
    }
}
