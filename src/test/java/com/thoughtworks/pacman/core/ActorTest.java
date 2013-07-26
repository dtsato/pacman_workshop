package com.thoughtworks.pacman.core;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.thoughtworks.pacman.core.movement.MovementStrategy;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActorTest {

    public class TestActor extends Actor {

        private boolean halted;

        public TestActor(Maze maze, SpacialCoordinate center) {
            this(maze, center, false);
        }

        public TestActor(Maze maze, SpacialCoordinate center, boolean halted) {
            super(maze, center);
            this.halted = halted;
        }

        @Override
        public boolean isHalted() {
            return halted;
        }

        @Override
        protected MovementStrategy getMovementStrategy() {
            return movementStrategy;
        }
    }

    private Maze maze;

    @Mock
    private MovementStrategy movementStrategy;

    @Before
    public void setUp() throws Exception {
        maze = MazeBuilder.buildDefaultMaze();
    }

    @Test
    public void advance_shouldNotMove_whenHalted() throws Exception {
        SpacialCoordinate center = new SpacialCoordinate(10 * Tile.SIZE + 1, 5 * Tile.SIZE + Tile.SIZE / 2);
        Actor actor = new TestActor(maze, center, true);

        actor.advance(70);

        assertThat(actor.getCenter(), equalTo(center));
    }

    @Test
    public void advance_shouldNotMove_whenCurrentPositionIsCenterOfNextTile() throws Exception {
        int initialX = 10 * Tile.SIZE + Tile.SIZE / 2;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        when(movementStrategy.getNextTile(center.toTileCoordinate())).thenReturn(new TileCoordinate(10, 5));
        Actor actor = new TestActor(maze, center);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX, initialY)));
    }

    @Test
    public void advance_shouldMoveToTileCenter_whenCurrentPositionIsInsideNextTile() throws Exception {
        int initialX = 10 * Tile.SIZE + 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        when(movementStrategy.getNextTile(center.toTileCoordinate())).thenReturn(new TileCoordinate(10, 5));
        Actor actor = new TestActor(maze, center);

        actor.advance(70);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY)));
    }

    @Test
    public void advance_shouldMoveToNextTileCenter_whenCurrentPositionIsOutsideButWithinDistanceOfNextTileCenter()
            throws Exception {
        int initialX = 10 * Tile.SIZE - 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        when(movementStrategy.getNextTile(center.toTileCoordinate())).thenReturn(new TileCoordinate(10, 5));
        Actor actor = new TestActor(maze, center);

        actor.advance(90);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY)));
    }

    @Test
    public void advance_shouldMoveToNextTileCenterAndTurn_whenDistanceIsGreaterThanDistanceToNextTileCenter()
            throws Exception {
        int initialX = 10 * Tile.SIZE - 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        when(movementStrategy.getNextTile(any(TileCoordinate.class))).thenReturn(new TileCoordinate(10, 5), new TileCoordinate(10, 6));
        Actor actor = new TestActor(maze, center);

        actor.advance(120);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY + 3)));
    }

    @Test
    public void advance_shouldMoveToCurrentTileCenterAndTurn_whenNextTileCenterIsDiagonalToCurrentPosition()
            throws Exception {
        int initialX = 10 * Tile.SIZE + 1;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        when(movementStrategy.getNextTile(center.toTileCoordinate())).thenReturn(new TileCoordinate(10, 6));
        Actor actor = new TestActor(maze, center);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(10 * Tile.SIZE + Tile.SIZE / 2, initialY + 3)));
    }

    @Test
    public void advance_shouldGetCloserToTileCenter_whenTileCenterIsTooFarFromCurrentPosition() throws Exception {
        int initialX = 10 * Tile.SIZE + Tile.SIZE / 2;
        int initialY = 5 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        when(movementStrategy.getNextTile(center.toTileCoordinate())).thenReturn(new TileCoordinate(11, 5));
        Actor actor = new TestActor(maze, center);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(initialX + 10, initialY)));
    }

    @Test
    public void advance_shouldTeleport_toTheRight() throws Exception {
        int initialX = 27 * Tile.SIZE + Tile.SIZE / 2;
        int initialY = 17 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        when(movementStrategy.getNextTile(center.toTileCoordinate())).thenReturn(new TileCoordinate(28, 17));
        Actor actor = new TestActor(maze, center);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(2, initialY)));
    }

    @Test
    public void advance_shouldTeleport_toTheLeft() throws Exception {
        int initialX = Tile.SIZE / 2;
        int initialY = 17 * Tile.SIZE + Tile.SIZE / 2;
        SpacialCoordinate center = new SpacialCoordinate(initialX, initialY);
        when(movementStrategy.getNextTile(center.toTileCoordinate())).thenReturn(new TileCoordinate(-1, 17));
        Actor actor = new TestActor(maze, center);

        actor.advance(100);

        assertThat(actor.getCenter(), equalTo(new SpacialCoordinate(28 * Tile.SIZE - 2, initialY)));
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
