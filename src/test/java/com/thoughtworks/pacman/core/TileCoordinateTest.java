package com.thoughtworks.pacman.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class TileCoordinateTest {

    @Test
    public void add_shouldSumCoordinates() {
        final TileCoordinate coordinate = new TileCoordinate(1, 1);
        assertThat(coordinate.add(coordinate), equalTo(new TileCoordinate(2, 2)));
    }

    @Test
    public void subtract_shouldSubtractCoordinates() {
        final TileCoordinate coordinate = new TileCoordinate(1, 1);
        assertThat(coordinate.subtract(coordinate), equalTo(new TileCoordinate(0, 0)));
    }

    @Test
    public void magnitude_shouldCalculateLengthOfVector() {
        assertThat(new TileCoordinate(0, 1).magnitude(), is(1.0));
        assertThat(new TileCoordinate(1, 0).magnitude(), is(1.0));
        assertThat(new TileCoordinate(3, 4).magnitude(), is(5.0));
    }

    @Test
    public void distanceTo_shouldCalculateDistanceToAnotherTileCoordinate() {
        assertThat(new TileCoordinate(0, 1).distanceTo(new TileCoordinate(3, 5)), is(5.0));
        assertThat(new TileCoordinate(1, 1).distanceTo(new TileCoordinate(3, 3)), is(Math.sqrt(8)));
    }

    @Test
    public void times_shouldMultipleCoordinates() {
        final TileCoordinate coordinate = new TileCoordinate(2, 4);
        assertThat(coordinate.times(3), equalTo(new TileCoordinate(6, 12)));
    }

    @Test
    public void toSpacialCoordinate_shouldConvertToSpacialCoordinateOfTileCenter() throws Exception {
        TileCoordinate coordinate = new TileCoordinate(1, 2);
        assertThat(coordinate.toSpacialCoordinate(), equalTo(new SpacialCoordinate(24, 40)));
    }

    @Test
    public void toSpacialCoordinate_shouldConvertToSpacialCoordinateOfTileCenterInNegativeSpace() throws Exception {
        TileCoordinate coordinate = new TileCoordinate(-1, -2);
        assertThat(coordinate.toSpacialCoordinate(), equalTo(new SpacialCoordinate(-8, -24)));
    }

    @Test
    public void toSpacialCoordinate_shouldConvertToSpacialCoordinateOfTileCenterWithZero() throws Exception {
        TileCoordinate coordinate = new TileCoordinate(0, 0);
        assertThat(coordinate.toSpacialCoordinate(), equalTo(new SpacialCoordinate(8, 8)));
    }

    @Test
    public void toString_shouldReturnCoordinates() {
        assertThat(new TileCoordinate(1, 1).toString(), equalTo("[ 1, 1]"));
        assertThat(new TileCoordinate(10, 10).toString(), equalTo("[10,10]"));
    }

    @Test
    public void equals_shouldBeTrue_whenCoordinatesAreEqual() {
        assertTrue(new TileCoordinate(1, 1).equals(new TileCoordinate(1, 1)));
    }

    @Test
    public void equals_shouldBeFalse_whenCoordinatesAreDifferent() {
        assertFalse(new TileCoordinate(1, 2).equals(new TileCoordinate(1, 1)));
        assertFalse(new TileCoordinate(1, 2).equals(new TileCoordinate(2, 2)));
    }

    @Test
    public void equals_shouldBeTrue_whenInstanceIsTheSame() {
        final TileCoordinate coordinate = new TileCoordinate(1, 1);
        assertTrue(coordinate.equals(coordinate));
    }

    @Test
    public void equals_shouldBeFalse_whenComparedToAnotherClass() {
        final TileCoordinate coordinate = new TileCoordinate(1, 1);
        assertFalse(coordinate.equals("Blah"));
    }

    @Test
    public void equals_shouldBeFalse_whenComparedToNull() {
        assertFalse(new TileCoordinate(1, 1).equals(null));
    }

    @Test
    public void hashCode_shouldBeSame_whenPositionsAreEqual() {
        final TileCoordinate coordinate = new TileCoordinate(1, 1);
        final TileCoordinate sameCoordinate = new TileCoordinate(1, 1);

        assertThat(coordinate.hashCode(), equalTo(sameCoordinate.hashCode()));
    }

    @Test
    public void hashCode_shouldBeDifferent_whenPositionsAreDifferent() {
        final TileCoordinate coordinate = new TileCoordinate(1, 1);
        final TileCoordinate differentCoordinate = new TileCoordinate(1, 2);

        assertThat(coordinate.hashCode(), not(equalTo(differentCoordinate.hashCode())));
    }
}
