package com.thoughtworks.pacman.core.tiles;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FreezingItemTest {

    @Test
    public void shouldBeMovable() {
        assertTrue(new FreezingItem(null, null).isMovable());
    }

    @Test
    public void isEaten_shouldBeFalseByDefault() {
        final FreezingItem item = new FreezingItem(null, null);
        assertFalse(item.isEaten());
    }

    @Test
    public void isEaten_shouldBeTrueAfterEat() {
        final FreezingItem item = new FreezingItem(null, null);

        item.eat();

        assertTrue(item.isEaten());
    }

}
