package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.core.tiles.FreezingItemBomb;

public class PacmanTileVisitor implements TileVisitor<Void> {

    public Void visit(Dot dot) {
        dot.eat();
        return null;
    }

    public Void visit(FreezingItem item) {
        item.eat();
        return null;
    }

    public Void visit(FreezingItemBomb freezingItemBomb) {
        return null;
    }

    public Void visit(Wall wall) {
        return null;
    }

    public Void visit(EmptyTile emptyTile) {
        return null;
    }

    public Void visit(Door door) {
        return null;
    }

}
