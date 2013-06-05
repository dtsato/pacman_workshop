package com.thoughtworks.pacman.core;

import org.junit.Test;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TileCoordinateTest {
    @Test
    public void add_shouldSumCoordinates() {
        final TileCoordinate tileCoordinate = new TileCoordinate(1, 1);
        assertThat(tileCoordinate.add(tileCoordinate), equalTo(new TileCoordinate(2, 2)));
    }

    @Test
    public void times_shouldMultipleCoordinates() {
        final TileCoordinate tileCoordinate = new TileCoordinate(2, 4);
        assertThat(tileCoordinate.times(3), equalTo(new TileCoordinate(6, 12)));
    }

    @Test
    public void toPoint_shouldConvertToPoint() {
        final TileCoordinate tileCoordinate = new TileCoordinate(2, 4);
        assertThat(tileCoordinate.toPoint(), equalTo(new Point(2, 4)));
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
        final TileCoordinate tileCoordinate = new TileCoordinate(1, 1);
        assertTrue(tileCoordinate.equals(tileCoordinate));
    }

    @Test
    public void equals_shouldBeFalse_whenComparedToAnotherClass() {
        final TileCoordinate tileCoordinate = new TileCoordinate(1, 1);
        assertFalse(tileCoordinate.equals("Blah"));
    }

    @Test
    public void equals_shouldBeFalse_whenComparedToNull() {
        assertFalse(new TileCoordinate(1, 1).equals(null));
    }

    @Test
    public void hashCode_shouldBeSame_whenPositionsAreEqual() {
        final TileCoordinate tileCoordinate = new TileCoordinate(1, 1);
        final TileCoordinate sameTileCoordinate = new TileCoordinate(1, 1);

        assertThat(tileCoordinate.hashCode(), equalTo(sameTileCoordinate.hashCode()));
    }

    @Test
    public void hashCode_shouldBeDifferent_whenPositionsAreDifferent() {
        final TileCoordinate tileCoordinate = new TileCoordinate(1, 1);
        final TileCoordinate differentTileCoordinate = new TileCoordinate(1, 2);

        assertThat(tileCoordinate.hashCode(), not(equalTo(differentTileCoordinate.hashCode())));
    }
}
