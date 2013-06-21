package com.thoughtworks.pacman.ui.presenters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.Rectangle;

import org.junit.Test;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.MazeBuilder;

public class PacmanPresenterTest {
    @Test
    public void shouldCalculateBoundInPixels() throws Exception {
        PacmanPresenter presenter = new PacmanPresenter(new Pacman(MazeBuilder.buildDefaultMaze()));
        assertThat(presenter.getBounds(), equalTo(new Rectangle(216, 416, Tile.SIZE, Tile.SIZE)));
    }

    @Test
    public void shouldAnimateMouth() throws Exception {
        PacmanPresenter presenter = new PacmanPresenter(new Pacman(MazeBuilder.buildDefaultMaze()));
        for (int i = 0; i < 5; i++) {
            assertThat(presenter.getArcAngle(), equalTo(360));
        }
        for (int i = 0; i < 5; i++) {
            assertThat(presenter.getArcAngle(), equalTo(280));
        }
        for (int i = 0; i < 5; i++) {
            assertThat(presenter.getArcAngle(), equalTo(360));
        }
    }

    @Test
    public void shouldAnimateDying() throws Exception {
        Pacman pacman = new Pacman(MazeBuilder.buildDefaultMaze());
        pacman.die();
        PacmanPresenter presenter = new PacmanPresenter(pacman);

        for (int i = 0; i < 36; i++) {
            assertThat(presenter.getArcAngle(), equalTo(360 - i * 10));
        }
        assertThat(presenter.getArcAngle(), equalTo(0));
        assertThat(presenter.getArcAngle(), equalTo(0));
    }
}
