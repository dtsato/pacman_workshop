package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Position;
import org.junit.Test;

import java.awt.Dimension;
import java.awt.Point;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WallPresenterTest {
    @Test
    public void shouldCalculatePositionInPixels() throws Exception {
        WallPresenter presenter = new WallPresenter(null, new Position(2, 3));
        assertThat(presenter.getPosition(), equalTo(new Point(2 * 16, 3 * 16)));
    }

    @Test
    public void shouldCalculateDimensionInPixels() throws Exception {
        WallPresenter presenter = new WallPresenter(null, new Position(1, 1));
        assertThat(presenter.getDimension(), equalTo(new Dimension(16, 16)));
    }
}
