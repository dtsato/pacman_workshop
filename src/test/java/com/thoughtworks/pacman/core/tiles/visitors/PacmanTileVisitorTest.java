package com.thoughtworks.pacman.core.tiles.visitors;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thoughtworks.pacman.core.tiles.Dot;

public class PacmanTileVisitorTest {
    @Test
    public void visitDot_shouldEat() {
        PacmanTileVisitor visitor = new PacmanTileVisitor();
        Dot dot = new Dot(null);

        visitor.visit(dot);

        assertTrue(dot.isEaten());
    }
}
