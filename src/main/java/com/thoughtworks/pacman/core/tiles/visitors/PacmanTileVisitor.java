package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class PacmanTileVisitor implements TileVisitor<Void> {

    public Void visit(Dot dot) {
        dot.eat();
        return null;
    }

    public Void visit(Dot dot, int xOffSet) {
        dot.eat();
        return null;
    }

    public Void visit(Wall wall) {
        return null;
    }

    public Void visit(Wall wall, int xOffSet) {
        return null;
    }

    public Void visit(EmptyTile emptyTile) {
        return null;
    }

    public Void visit(EmptyTile emptyTile, int xOffSet) {
        return null;
    }

    public Void visit(Door door) {
        return null;
    }

    public Void visit(Door door, int xOffSet) {
        return null;
    }
}
