package com.thoughtworks.pacman.core;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ActorTest {

    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = new Maze();
    }

    @Test
    public void move_shouldUpdatePosition_whenNextTileIsValid() {
        final Actor actor = new Actor(maze, new Position(1, 4), Direction.RIGHT);
        actor.move();
        assertThat(actor.getPosition(), equalTo(new Position(2, 4)));
    }

    @Test
    public void move_shouldUpdatePositionBasedOnCurrentDirection_whenNextTileIsValid() {
        final Actor actor = new Actor(maze, new Position(1, 4), Direction.DOWN);
        actor.move();
        assertThat(actor.getPosition(), equalTo(new Position(1, 5)));
    }

    @Test
    public void move_shouldNotUpdatePosition_whenNextTileIsInvalid() {
        final Actor actor = new Actor(maze, new Position(1, 4), Direction.LEFT);
        actor.move();
        assertThat(actor.getPosition(), equalTo(new Position(1, 4)));
    }
}
