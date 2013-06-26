package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.awt.Rectangle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PacmanPresenterTest {
    @Test
    public void shouldCalculateBoundInPixels() throws Exception {
        PacmanPresenter presenter = new PacmanPresenter(new Pacman(MazeBuilder.buildDefaultMaze()));
        assertThat(presenter.getBounds(), equalTo(new Rectangle(214, 414, PacmanPresenter.DIAMETER, PacmanPresenter.DIAMETER)));
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

        for (int i = 1; i <= PacmanPresenter.MOUTH_CLOSED / PacmanPresenter.DEATH_FRAMES; i++) {
            assertThat(presenter.getArcAngle(), equalTo(PacmanPresenter.MOUTH_CLOSED - i * PacmanPresenter.DEATH_FRAMES));
        }
        assertThat(presenter.getArcAngle(), equalTo(0));
        assertThat(presenter.getArcAngle(), equalTo(0));
    }

    @Test
    public void isDying_shouldBeFalseByDefault() throws Exception {
        PacmanPresenter presenter = new PacmanPresenter(new Pacman(MazeBuilder.buildDefaultMaze()));
        Assert.assertThat(presenter.isDying(), is(false));
    }

    @Test
    public void die_shouldKillPacmanSlowly() throws Exception {
        Pacman pacman = new Pacman(MazeBuilder.buildDefaultMaze());
        PacmanPresenter presenter = new PacmanPresenter(pacman);

        pacman.die();

        int totalDeathFrames = PacmanPresenter.MOUTH_CLOSED / PacmanPresenter.DEATH_FRAMES + PacmanPresenter.DELAY_AFTER_DEAD;
        for (int times = 1; times <= totalDeathFrames + 1; times++) {
            assertThat(times + "th time", presenter.isDying(), is(true));
        }

        assertThat(presenter.isDying(), is(false));
    }
}
