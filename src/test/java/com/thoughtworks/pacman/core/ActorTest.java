package com.thoughtworks.pacman.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ActorTest {

    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = new Maze();
    }

    @Test
    public void advanceShouldUpdateCenter() throws Exception {
        int initialX = 14 * Tile.SIZE;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.LEFT);

        actor.advance(1000);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX - 100, initialY)));
    }

    @Test
    public void advanceShouldStayPutWhenFacingWall() throws Exception {
        int initialX = 14 * Tile.SIZE;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.UP);

        actor.advance(1000);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY)));
    }

    @Test
    public void advanceShouldMoveWhenGoingTowardsWall() throws Exception {
        int initialX = 7 * Tile.SIZE - 1;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.LEFT);

        actor.advance(90);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX - 7, initialY)));
    }

    @Test
    public void advanceShouldChangeToNextDirectionWhenPossibleBeforeMoving() throws Exception {
        int initialX = 7 * Tile.SIZE - Tile.SIZE / 2;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.LEFT);

        actor.setNextDirection(Direction.UP);
        actor.advance(1000);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY - 100)));
        assertThat(actor.getDirection(), equalTo(Direction.UP));
    }
}
