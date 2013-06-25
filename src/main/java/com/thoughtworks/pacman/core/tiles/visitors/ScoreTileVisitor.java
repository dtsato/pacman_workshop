package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.tiles.Door;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class ScoreTileVisitor implements TileVisitor<Integer> {
    private static final int SCORE_PER_DOT = 10;

    @Override
    public Integer visit(Dot dot) {
        return dot.isEaten() ? SCORE_PER_DOT : 0;
    }

    @Override
    public Integer visit(Wall wall) {
        return 0;
    }

    @Override
    public Integer visit(EmptyTile emptyTile) {
        return 0;
    }

    @Override
    public Integer visit(Door door) {
        return 0;
    }
}
