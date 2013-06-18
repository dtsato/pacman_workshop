package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Maze;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.actors.Ghost;
import org.junit.Test;

import java.awt.Color;
import java.awt.Rectangle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GhostPresenterTest {
    @Test
    public void shouldCalculateBoundInPixels() throws Exception {
        GhostPresenter presenter = new GhostPresenter(new Ghost(new Maze(), new SpacialCoordinate(Tile.SIZE / 2, Tile.SIZE / 2)), Color.red);
        assertThat(presenter.getBounds(), equalTo(new Rectangle(0, 0, Tile.SIZE, Tile.SIZE)));
    }
}
