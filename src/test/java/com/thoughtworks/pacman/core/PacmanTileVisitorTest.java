package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.Dot;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class PacmanTileVisitorTest {

    private Pacman pacman;

    @Before
    public void setUp() throws Exception {
        pacman = new Pacman(MazeBuilder.buildDefaultMaze());
    }

    @Test
    public void visitDot_shouldEat_whenDotIsNotEaten() {
        PacmanTileVisitor visitor = new PacmanTileVisitor(pacman);
        Dot dot = new Dot(null);

        visitor.visit(dot);

        assertTrue(dot.isEaten());
        assertThat(pacman.getScore(), equalTo(10));
    }

    @Test
    public void visitDot_shouldNotEatAgain_whenDotIsAlreadyEaten() {
        PacmanTileVisitor visitor = new PacmanTileVisitor(pacman);
        Dot dot = new Dot(null);
        dot.eat();

        visitor.visit(dot);

        assertThat(pacman.getScore(), equalTo(0));
    }
}
