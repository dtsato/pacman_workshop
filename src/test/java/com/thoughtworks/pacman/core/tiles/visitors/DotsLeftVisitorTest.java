package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DotsLeftVisitorTest {
    @Test
    public void visitingDot_shouldCountOne_whenDotIsNotEaten() {
        DotsLeftVisitor scoreTileVisitor = new DotsLeftVisitor();
        Dot dot = new Dot(null);

        assertThat(scoreTileVisitor.visit(dot), equalTo(1));
    }

    @Test
    public void visitingDot_shouldCountZero_whenDotIsEaten() {
        DotsLeftVisitor scoreTileVisitor = new DotsLeftVisitor();
        Dot dot = new Dot(null);

        dot.eat();

        assertThat(scoreTileVisitor.visit(dot), equalTo(0));
    }

    @Test
    public void visitingEmptyTile_shouldCountZero() {
        DotsLeftVisitor scoreTileVisitor = new DotsLeftVisitor();
        final EmptyTile emptyTile = new EmptyTile(null);

        assertThat(scoreTileVisitor.visit(emptyTile), equalTo(0));
    }

    @Test
    public void visitingWall_shouldCountZero() {
        DotsLeftVisitor scoreTileVisitor = new DotsLeftVisitor();
        final Wall wall = new Wall(null);

        assertThat(scoreTileVisitor.visit(wall), equalTo(0));
    }
}
