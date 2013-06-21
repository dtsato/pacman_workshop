package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class PacmanTileVisitorTest {
    @Test
    public void visitDot_shouldEat() {
        PacmanTileVisitor visitor = new PacmanTileVisitor();
        Dot dot = new Dot(null);

        visitor.visit(dot);

        assertTrue(dot.isEaten());
    }
}
