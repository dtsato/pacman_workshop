package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import org.junit.Test;

import java.awt.Rectangle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DotPresenterTest {
    @Test
    public void shouldCalculateDotBoundsInPixels() throws Exception {
        DotPresenter presenter = new DotPresenter(new Dot(new TileCoordinate(2, 3)));
        assertThat(presenter.getBounds(), equalTo(new Rectangle(38, 54, 4, 4)));
    }
}
