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

}
