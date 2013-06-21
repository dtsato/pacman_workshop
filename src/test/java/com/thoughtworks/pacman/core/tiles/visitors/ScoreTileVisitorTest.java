package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ScoreTileVisitorTest {
    @Test
    public void visitingDot_shouldCountZeroPoints_whenDotIsNotEaten() {
        ScoreTileVisitor scoreTileVisitor = new ScoreTileVisitor();
        Dot dot = new Dot(null);

        assertThat(scoreTileVisitor.visit(dot), equalTo(0));
    }

    @Test
    public void visitingDot_shouldCountTenPoints_whenDotIsEaten() {
        ScoreTileVisitor scoreTileVisitor = new ScoreTileVisitor();
        Dot dot = new Dot(null);

        dot.eat();

        assertThat(scoreTileVisitor.visit(dot), equalTo(10));
    }

    @Test
    public void visitingEmptyTile_shouldCountZeroPoints() {
        ScoreTileVisitor scoreTileVisitor = new ScoreTileVisitor();
        final EmptyTile emptyTile = new EmptyTile(null);

        assertThat(scoreTileVisitor.visit(emptyTile), equalTo(0));
    }

    @Test
    public void visitingWall_shouldCountZeroPoints() {
        ScoreTileVisitor scoreTileVisitor = new ScoreTileVisitor();
        final Wall wall = new Wall(null);

        assertThat(scoreTileVisitor.visit(wall), equalTo(0));
    }
}
