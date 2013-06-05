package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.presenters.DotPresenter;
import com.thoughtworks.pacman.ui.presenters.NullPresenter;
import com.thoughtworks.pacman.ui.presenters.WallPresenter;

public class TileToPresenterFactory implements TileVisitor<Presenter> {
    public static Presenter toPresenter(Tile tile) {
        final TileToPresenterFactory factory = new TileToPresenterFactory();
        return tile.visit(factory);
    }

    @Override
    public Presenter visit(Dot dot) {
        return new DotPresenter(dot);
    }

    @Override
    public Presenter visit(Wall wall) {
        return new WallPresenter(wall);
    }

    @Override
    public Presenter visit(EmptyTile emptyTile) {
        return new NullPresenter();
    }
}
