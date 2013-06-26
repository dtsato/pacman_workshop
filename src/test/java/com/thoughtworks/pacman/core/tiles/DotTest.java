package com.thoughtworks.pacman.core.tiles;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DotTest {

    @Test
    public void shouldBeMovable() {
        assertTrue(new Dot(null, null).isMovable());
    }

    @Test
    public void isEaten_shouldBeFalseByDefault() {
        final Dot dot = new Dot(null, null);
        assertFalse(dot.isEaten());
    }

    @Test
    public void isEaten_shouldBeTrueAfterEat() {
        final Dot dot = new Dot(null, null);

        dot.eat();

        assertTrue(dot.isEaten());
    }

    @Test
    public void toString_shouldReturnDot() {
        assertThat(new Dot(null, null).toString(), equalTo("."));
    }
}
