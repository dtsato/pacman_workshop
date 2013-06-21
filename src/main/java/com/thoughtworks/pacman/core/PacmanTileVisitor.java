package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class PacmanTileVisitor implements TileVisitor<Void> {
    private Pacman pacman;

    public PacmanTileVisitor(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public Void visit(Dot dot) {
        if (!dot.isEaten()) {
            pacman.eat();
            dot.eat();
        }
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
