package com.thoughtworks.pacman.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class ActorTest {

    public class TestActor extends Actor {

        private TileCoordinate[] nextTiles;
        private int nextTileIndex;

        public TestActor(Maze maze, SpacialCoordinate center, TileCoordinate... nextTiles) {
            super(maze, center, null);
            this.nextTiles = nextTiles;
            this.nextTileIndex = 0;
        }

        @Override
        protected TileCoordinate getNextTile(TileCoordinate currentTile) {
            return nextTiles[nextTileIndex++];
        }
    }

    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
    }

    @Test
    public void advance2_shouldNotMove_whenCurrentPositionIsCenterOfNextTile() throws Exception {
        int initialX = 10 * Tile.SIZE + Tile.SIZE / 2;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 5));

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY)));
    }

    @Test
    public void advance2_shouldMoveToTileCenter_whenCurrentPositionIsInsideNextTile() throws Exception {
        int initialX = 10 * Tile.SIZE + 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 5));

        actor.advance(70);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY)));
    }

    @Test
    public void advance2_shouldMoveToNextTileCenter_whenCurrentPositionIsOutsideButWithinDistanceOfNextTileCenter()
            throws Exception {
        int initialX = 10 * Tile.SIZE - 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 5));

        actor.advance(90);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY)));
    }

    @Test
    public void advance2_shouldMoveToNextTileCenterAndTurn_whenDistanceIsGreaterThanDistanceToNextTileCenter()
            throws Exception {
        int initialX = 10 * Tile.SIZE - 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 5), new TileCoordinate(10, 6));

        actor.advance(120);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY + 3)));
    }

    @Test
    public void advance2_shouldMoveToCurrentTileCenterAndTurn_whenNextTileCenterIsDiagonalToCurrentPosition()
            throws Exception {
        int initialX = 10 * Tile.SIZE + 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 6));

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY + 3)));
    }

    @Test
    public void advance2_shouldGetCloserToTileCenter_whenTileCenterIsTooFarFromCurrentPosition() throws Exception {
        int initialX = 10 * Tile.SIZE + Tile.SIZE / 2;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(11, 5));

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + 10, initialY)));
    }

    @Test
    @Ignore("Must re-implement teleport")
    public void advance_shouldTeleport_whenPossible() throws Exception {
        int initialX = -Tile.SIZE;
        int initialY = 17 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(Tile.SIZE * maze.getWidth() - Tile.SIZE / 2,
                initialY)));
    }

    @Test
    public void collidesWith_shouldBeTrue_whenOtherActorIsInSameTile() throws Exception {
        Actor actor1 = new TestActor(maze, new SpacialCoordinate(15, 15));
        Actor actor2 = new TestActor(maze, new SpacialCoordinate(10, 10));

        assertThat(actor1.collidesWith(actor2), is(true));
        assertThat(actor2.collidesWith(actor1), is(true));
    }

    @Test
    public void collidesWith_shouldBeFalse_whenOtherActorIsInDifferentTile() throws Exception {
        Actor actor1 = new TestActor(maze, new SpacialCoordinate(15, 15));
        Actor actor2 = new TestActor(maze, new SpacialCoordinate(17, 17));

        assertThat(actor1.collidesWith(actor2), is(false));
        assertThat(actor2.collidesWith(actor1), is(false));
    }
}
