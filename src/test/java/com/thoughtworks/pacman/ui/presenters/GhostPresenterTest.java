package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.GhostType;
import org.junit.Test;

import java.awt.Point;
import java.awt.Rectangle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GhostPresenterTest {
    @Test
    public void shouldCalculateBoundInPixels() throws Exception {
        Game game = new Game();
        GhostPresenter presenter = new GhostPresenter(new Ghost(game, GhostType.BLINKY));
        Point point = GhostType.BLINKY.getStartCoordinate().toPoint();
        assertThat(presenter.getBounds(), equalTo(new Rectangle(point.x - 10, point.y - 10, 20, 20)));
    }
}
