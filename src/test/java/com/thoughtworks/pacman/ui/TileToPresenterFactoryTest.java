package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.presenters.DotPresenter;
import com.thoughtworks.pacman.ui.presenters.NullPresenter;
import com.thoughtworks.pacman.ui.presenters.WallPresenter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class TileToPresenterFactoryTest {
    @Test
    public void toPresenter_shouldReturnWallPresenter_whenTileIsWall() {
        Presenter presenter = TileToPresenterFactory.toPresenter(new Wall((TileCoordinate)null, (String)null));
        assertThat(presenter, instanceOf(WallPresenter.class));
    }

    @Test
    public void toPresenter_shouldReturnDotPresenter_whenTileIsDot() {
        Presenter presenter = TileToPresenterFactory.toPresenter(new Dot(new TileCoordinate(1, 1), null));
        assertThat(presenter, instanceOf(DotPresenter.class));
    }

    @Test
    public void toPresenter_shouldReturnNullPresenter_whenTileIsEmptyTile() {
        Presenter presenter = TileToPresenterFactory.toPresenter(new EmptyTile(null, null));
        assertThat(presenter, instanceOf(NullPresenter.class));
    }
}
