package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.presenters.DotPresenter;
import com.thoughtworks.pacman.ui.presenters.WallPresenter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TileToPresenterFactoryTest {
    @Test
    public void toPresenter_shouldReturnWallPresenter_whenTileIsWall() {
        Presenter presenter = TileToPresenterFactory.toPresenter(new Wall(null, null));
        assertThat(presenter, instanceOf(WallPresenter.class));
    }

    @Test
    public void toPresenter_shouldReturnDotPresenter_whenTileIsDot() {
        Presenter presenter = TileToPresenterFactory.toPresenter(new Dot(null, null));
        assertThat(presenter, instanceOf(DotPresenter.class));
    }

    @Test
    public void toPresenter_shouldReturnNull_whenTileIsEmptyTile() {
        Presenter presenter = TileToPresenterFactory.toPresenter(new EmptyTile(null, null));
        assertThat(presenter, nullValue());
    }
}
