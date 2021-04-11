package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Wall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Graphics2D;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WallPresenterTest {
    @Mock
    private Graphics2D graphics;

    @Test
    public void shouldDrawWallTypeShape() throws Exception {
        Wall wall = new Wall(new TileCoordinate(2, 3));
        WallPresenter presenter = new WallPresenter(wall);

        presenter.draw(graphics);

        verify(graphics).draw(wall.getShape());
    }

    @Test
    public void shouldDrawWallTypeShapeWithOffset() throws Exception {
        Wall wall = new Wall(new TileCoordinate(2, 3));
        WallPresenter presenter = new WallPresenter(wall, 100);

        presenter.draw(graphics);

        verify(graphics).draw(presenter.getWall().getShape());
    }
}
