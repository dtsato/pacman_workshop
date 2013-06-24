package com.thoughtworks.pacman.ui.presenters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.Rectangle;

import com.thoughtworks.pacman.core.Direction;
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
            assertThat(presenter.getArcAngle(), equalTo(PacmanPresenter.MOUTH_CLOSED));
        }
        for (int i = 0; i < 5; i++) {
            assertThat(presenter.getArcAngle(), equalTo(PacmanPresenter.MOUTH_OPENED));
        }
        for (int i = 0; i < 5; i++) {
            assertThat(presenter.getArcAngle(), equalTo(PacmanPresenter.MOUTH_CLOSED));
        }
    }

    @Test
    public void shouldDisplayOpenMouthWhenNotMoving() throws Exception {
        Pacman pacman = new Pacman(MazeBuilder.buildDefaultMaze());
        PacmanPresenter presenter = new PacmanPresenter(pacman);
        for (int i = 0; i < 5; i++) {
            pacman.advance(1000);
        }
        assertThat(pacman.isMoving(), is(false));

        assertThat(presenter.getStartAngle(), equalTo(Direction.LEFT.getStartAngle()));
        assertThat(presenter.getArcAngle(), equalTo(PacmanPresenter.MOUTH_OPENED));
    }

    @Test
    public void shouldAnimateDying() throws Exception {
        Pacman pacman = new Pacman(MazeBuilder.buildDefaultMaze());
        pacman.die();
        PacmanPresenter presenter = new PacmanPresenter(pacman);

        for (int i = 0; i < PacmanPresenter.MOUTH_CLOSED / 10; i++) {
            assertThat(presenter.getArcAngle(), equalTo(PacmanPresenter.MOUTH_CLOSED - i * 10));
        }
        assertThat(presenter.getArcAngle(), equalTo(0));
        assertThat(presenter.getArcAngle(), equalTo(0));
    }
}
