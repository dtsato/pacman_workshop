package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public interface TileVisitor<T> {
    T visit(Dot dot);
    T visit(Wall dot);
    T visit(EmptyTile emptyTile);
}
