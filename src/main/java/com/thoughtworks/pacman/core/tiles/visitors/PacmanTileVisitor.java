package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class PacmanTileVisitor implements TileVisitor<Void> {

    @Override
    public Void visit(Dot dot) {
        dot.eat();
        return null;
    }

    @Override
    public Void visit(Wall wall) {
        return null;
    }

    @Override
    public Void visit(EmptyTile emptyTile) {
        return null;
    }
}
