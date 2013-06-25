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

        private Direction nextDirection;
        private TileCoordinate[] nextTiles;
        private int nextTileIndex;

        public TestActor(Maze maze, SpacialCoordinate center, Direction direction, Direction nextDirection) {
            super(maze, center, direction);
            this.nextDirection = nextDirection;
        }

        public TestActor(Maze maze, SpacialCoordinate center, TileCoordinate... nextTiles) {
            super(maze, center, null);
            this.nextTiles = nextTiles;
            this.nextTileIndex = 0;
        }

        @Override
        protected Direction getNextDirection(TileCoordinate tileCoordinate) {
            return nextDirection;
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

        actor.advance2(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY)));
    }

    @Test
    public void advance2_shouldMoveToTileCenter_whenCurrentPositionIsInsideNextTile() throws Exception {
        int initialX = 10 * Tile.SIZE + 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 5));

        actor.advance2(70);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY)));
    }

    @Test
    public void advance2_shouldMoveToNextTileCenter_whenCurrentPositionIsOutsideButWithinDistanceOfNextTileCenter()
            throws Exception {
        int initialX = 10 * Tile.SIZE - 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 5));

        actor.advance2(90);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY)));
    }

    @Test
    public void advance2_shouldMoveToNextTileCenterAndTurn_whenDistanceIsGreaterThanDistanceToNextTileCenter()
            throws Exception {
        int initialX = 10 * Tile.SIZE - 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 5), new TileCoordinate(10, 6));

        actor.advance2(120);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY + 3)));
    }

    @Test
    public void advance2_shouldMoveToCurrentTileCenterAndTurn_whenNextTileCenterIsDiagonalToCurrentPosition()
            throws Exception {
        int initialX = 10 * Tile.SIZE + 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(10, 6));

        actor.advance2(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY + 3)));
    }

    @Test
    public void advance2_shouldGetCloserToTileCenter_whenTileCenterIsTooFarFromCurrentPosition() throws Exception {
        int initialX = 10 * Tile.SIZE + Tile.SIZE / 2;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, new TileCoordinate(11, 5));

        actor.advance2(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + 10, initialY)));
    }

    @Test
    public void advance_shouldNotMove_whenDirectionIsNone() throws Exception {
        int initialX = 10 * Tile.SIZE;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        TestActor actor = new TestActor(maze, center, Direction.NONE, Direction.NONE);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY)));
    }

    @Test
    public void advance_shouldUpdateCenter_whenContinuouslyMovingInOneDirection() throws Exception {
        int initialX = 10 * Tile.SIZE;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        TestActor actor = new TestActor(maze, center, Direction.RIGHT, Direction.RIGHT);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + 10, initialY)));
    }

    @Test
    public void advance_shouldContinueInSameDirection_whenNotPassingThroughTileCenter() throws Exception {
        int initialX = 10 * Tile.SIZE - Tile.SIZE / 4;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        TestActor actor = new TestActor(maze, center, Direction.RIGHT, Direction.UP);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + 10, initialY)));
    }

    @Test
    public void advance_shouldChangeDirection_whenPassingThroughCurrentTileCenter() throws Exception {
        int initialX = 10 * Tile.SIZE + Tile.SIZE / 4;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        TestActor actor = new TestActor(maze, center, Direction.RIGHT, Direction.UP);

        actor.advance(100);

        int deltaX = Tile.SIZE / 4;
        int deltaY = 10 - deltaX;
        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + deltaX, initialY - deltaY)));
    }

    @Test
    public void advance_shouldChangeDirection_whenPassingThroughNextTileCenter() throws Exception {
        int initialX = 10 * Tile.SIZE - 2;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        TestActor actor = new TestActor(maze, center, Direction.RIGHT, Direction.UP);

        actor.advance(120);

        int newX = 10 * Tile.SIZE + Tile.SIZE / 2;
        int deltaY = 12 - (newX - initialX);
        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(newX, initialY - deltaY)));
    }

    @Test
    public void advance_shouldStopAtCenter_whenNextDirectionIsNone() throws Exception {
        int initialX = 10 * Tile.SIZE + 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        TestActor actor = new TestActor(maze, center, Direction.RIGHT, Direction.NONE);

        actor.advance(100);

        int newX = 10 * Tile.SIZE + Tile.SIZE / 2;
        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(newX, initialY)));
    }

    @Test
    public void advance_shouldStartMoving_whenNextDirectionIsNotNone() throws Exception {
        int initialX = 10 * Tile.SIZE + Tile.SIZE / 2;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        TestActor actor = new TestActor(maze, center, Direction.NONE, Direction.RIGHT);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + 10, initialY)));
    }

    @Test @Ignore
    public void advance_shouldTeleport_whenPossible() throws Exception {
        int initialX = -Tile.SIZE;
        int initialY = 17 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        Actor actor = new TestActor(maze, center, Direction.LEFT, Direction.LEFT);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(Tile.SIZE * maze.getWidth() - Tile.SIZE / 2,
                initialY)));
    }

    @Test
    public void collidesWith_shouldBeTrue_whenOtherActorIsInSameTile() throws Exception {
        Actor actor1 = new TestActor(maze, new SpacialCoordinate(15, 15), Direction.LEFT, Direction.LEFT);
        Actor actor2 = new TestActor(maze, new SpacialCoordinate(10, 10), Direction.RIGHT, Direction.RIGHT);

        assertThat(actor1.collidesWith(actor2), is(true));
        assertThat(actor2.collidesWith(actor1), is(true));
    }

    @Test
    public void collidesWith_shouldBeFalse_whenOtherActorIsInDifferentTile() throws Exception {
        Actor actor1 = new TestActor(maze, new SpacialCoordinate(15, 15), Direction.LEFT, Direction.LEFT);
        Actor actor2 = new TestActor(maze, new SpacialCoordinate(17, 17), Direction.RIGHT, Direction.RIGHT);

        assertThat(actor1.collidesWith(actor2), is(false));
        assertThat(actor2.collidesWith(actor1), is(false));
    }
}
