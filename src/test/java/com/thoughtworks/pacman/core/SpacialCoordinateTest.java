package com.thoughtworks.pacman.core;

import org.junit.Test;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SpacialCoordinateTest {
    @Test
    public void add_shouldSumCoordinates() {
        final SpacialCoordinate coordinate = new SpacialCoordinate(1, 1);
        assertThat(coordinate.add(coordinate), equalTo(new SpacialCoordinate(2, 2)));
    }

    @Test
    public void subtract_shouldSubtractCoordinates() {
        final SpacialCoordinate coordinate = new SpacialCoordinate(1, 1);
        assertThat(coordinate.subtract(coordinate), equalTo(new SpacialCoordinate(0, 0)));
    }

    @Test
    public void times_shouldMultipleCoordinates() {
        final SpacialCoordinate coordinate = new SpacialCoordinate(2, 4);
        assertThat(coordinate.times(3), equalTo(new SpacialCoordinate(6, 12)));
    }

    @Test
    public void magnitude_shouldCalculateLengthOfVector() {
        assertThat(new SpacialCoordinate(0, 1).magnitude(), equalTo(1));
        assertThat(new SpacialCoordinate(1, 0).magnitude(), equalTo(1));
        assertThat(new SpacialCoordinate(3, 4).magnitude(), equalTo(5));
    }

    @Test
    public void unit_shouldReturnUnitVector() throws Exception {
        assertThat(new SpacialCoordinate(4, 0).unit(), equalTo(new SpacialCoordinate(1, 0)));
        assertThat(new SpacialCoordinate(0, 4).unit(), equalTo(new SpacialCoordinate(0, 1)));
        assertThat(new SpacialCoordinate(-4, 0).unit(), equalTo(new SpacialCoordinate(-1, 0)));
        assertThat(new SpacialCoordinate(0, -4).unit(), equalTo(new SpacialCoordinate(0, -1)));
    }

    @Test
    public void toPoint_shouldConvertToPoint() {
        final SpacialCoordinate coordinate = new SpacialCoordinate(2, 4);
        assertThat(coordinate.toPoint(), equalTo(new Point(2, 4)));
    }

    @Test
    public void toString_shouldReturnCoordinates() {
        assertThat(new SpacialCoordinate(1, 1).toString(), equalTo("[ 1, 1]"));
        assertThat(new SpacialCoordinate(10, 10).toString(), equalTo("[10,10]"));
    }

    @Test
    public void equals_shouldBeTrue_whenCoordinatesAreEqual() {
        assertTrue(new SpacialCoordinate(1, 1).equals(new SpacialCoordinate(1, 1)));
    }

    @Test
    public void equals_shouldBeFalse_whenCoordinatesAreDifferent() {
        assertFalse(new SpacialCoordinate(1, 2).equals(new SpacialCoordinate(1, 1)));
        assertFalse(new SpacialCoordinate(1, 2).equals(new SpacialCoordinate(2, 2)));
    }

    @Test
    public void equals_shouldBeTrue_whenInstanceIsTheSame() {
        final SpacialCoordinate coordinate = new SpacialCoordinate(1, 1);
        assertTrue(coordinate.equals(coordinate));
    }

    @Test
    public void equals_shouldBeFalse_whenComparedToAnotherClass() {
        final SpacialCoordinate coordinate = new SpacialCoordinate(1, 1);
        assertFalse(coordinate.equals("Blah"));
    }

    @Test
    public void equals_shouldBeFalse_whenComparedToNull() {
        assertFalse(new SpacialCoordinate(1, 1).equals(null));
    }

    @Test
    public void hashCode_shouldBeSame_whenPositionsAreEqual() {
        final SpacialCoordinate coordinate = new SpacialCoordinate(1, 1);
        final SpacialCoordinate sameCoordinate = new SpacialCoordinate(1, 1);

        assertThat(coordinate.hashCode(), equalTo(sameCoordinate.hashCode()));
    }

    @Test
    public void hashCode_shouldBeDifferent_whenPositionsAreDifferent() {
        final SpacialCoordinate coordinate = new SpacialCoordinate(1, 1);
        final SpacialCoordinate differentCoordinate = new SpacialCoordinate(1, 2);

        assertThat(coordinate.hashCode(), not(equalTo(differentCoordinate.hashCode())));
    }
}
