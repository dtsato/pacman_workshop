package com.thoughtworks.pacman.core;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
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
    public void advance_shouldUpdateCenter() throws Exception {
        int initialX = 14 * Tile.SIZE;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.LEFT);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX - 10, initialY)));
    }

    @Test
    public void advance_shouldStayPutWhenFacingWall() throws Exception {
        int initialX = 14 * Tile.SIZE;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.UP);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY)));
    }

    @Test
    public void advance_shouldMoveWhenGoingTowardsWall() throws Exception {
        int initialX = 7 * Tile.SIZE - 1;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.LEFT);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX - 7, initialY)));
    }

    @Test
    public void advance_shouldChangeToNextDirectionWhenPossibleBeforeMoving() throws Exception {
        int initialX = 7 * Tile.SIZE - Tile.SIZE / 2;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.LEFT);

        actor.setNextDirection(Direction.UP);
        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY - 10)));
        assertThat(actor.getDirection(), equalTo(Direction.UP));
    }

    @Test
    public void advance_shouldKeepGoingInTheCurrentDirectionIfCantTurn() throws Exception {
        int initialX = 8 * Tile.SIZE - Tile.SIZE / 2;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.LEFT);

        actor.setNextDirection(Direction.UP);
        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX - 10, initialY)));
        assertThat(actor.getDirection(), equalTo(Direction.LEFT));
    }

    @Test
    public void advance_shouldTurnNearTheCenterOfTheTile() throws Exception {
        int initialX = 7 * Tile.SIZE - Tile.SIZE / 2;
        int initialY = 26 * Tile.SIZE + Tile.SIZE / 2 - 3;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.DOWN);

        actor.setNextDirection(Direction.RIGHT);
        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + 7, initialY + 3)));
        assertThat(actor.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void advance_shouldNotTurnAfterTheCenterOfTheTile() throws Exception {
        int initialX = 16 * Tile.SIZE - 3;
        int initialY = 32 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new Actor(maze, center, Direction.RIGHT);

        actor.setNextDirection(Direction.UP);
        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + 10, initialY)));
        assertThat(actor.getDirection(), equalTo(Direction.RIGHT));
    }

    @Test
    public void collidesWith_shouldBeTrueIfOtherActorIsInSameTile() throws Exception {
        Actor actor1 = new Actor(maze, new SpacialCoordinate(15, 15), Direction.LEFT);
        Actor actor2 = new Actor(maze, new SpacialCoordinate(10, 10), Direction.RIGHT);

        assertTrue(actor1.collidesWith(actor2));
        assertTrue(actor2.collidesWith(actor1));
    }

    @Test
    public void collidesWith_shouldBeFalseIfOtherActorIsInDifferentTile() throws Exception {
        Actor actor1 = new Actor(maze, new SpacialCoordinate(15, 15), Direction.LEFT);
        Actor actor2 = new Actor(maze, new SpacialCoordinate(17, 17), Direction.RIGHT);

        assertFalse(actor1.collidesWith(actor2));
        assertFalse(actor2.collidesWith(actor1));
    }
}
