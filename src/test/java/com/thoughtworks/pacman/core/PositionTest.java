package com.thoughtworks.pacman.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PositionTest {
    @Test
    public void add_shouldSumCoordinates() {
        final Position position = new Position(1, 1);
        assertThat(position.add(position), equalTo(new Position(2, 2)));
    }

    @Test
    public void toString_shouldReturnCoordinates() {
        assertThat(new Position(1, 1).toString(), equalTo("[ 1, 1]"));
        assertThat(new Position(10, 10).toString(), equalTo("[10,10]"));
    }

    @Test
    public void equals_shouldBeTrue_whenCoordinatesAreEqual() {
        assertTrue(new Position(1, 1).equals(new Position(1, 1)));
    }

    @Test
    public void equals_shouldBeFalse_whenCoordinatesAreDifferent() {
        assertFalse(new Position(1, 2).equals(new Position(1, 1)));
        assertFalse(new Position(1, 2).equals(new Position(2, 2)));
    }

    @Test
    public void equals_shouldBeTrue_whenInstanceIsTheSame() {
        final Position position = new Position(1, 1);
        assertTrue(position.equals(position));
    }

    @Test
    public void equals_shouldBeFalse_whenComparedToAnotherClass() {
        final Position position = new Position(1, 1);
        assertFalse(position.equals("Blah"));
    }

    @Test
    public void equals_shouldBeFalse_whenComparedToNull() {
        assertFalse(new Position(1, 1).equals(null));
    }

    @Test
    public void hashCode_shouldBeSame_whenPositionsAreEqual() {
        final Position position = new Position(1, 1);
        final Position samePosition = new Position(1, 1);

        assertThat(position.hashCode(), equalTo(samePosition.hashCode()));
    }

    @Test
    public void hashCode_shouldBeDifferent_whenPositionsAreDifferent() {
        final Position position = new Position(1, 1);
        final Position differentPosition = new Position(1, 2);

        assertThat(position.hashCode(), not(equalTo(differentPosition.hashCode())));
    }
}
