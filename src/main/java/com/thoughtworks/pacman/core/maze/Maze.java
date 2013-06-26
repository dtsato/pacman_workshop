package com.thoughtworks.pacman.core.maze;

import java.awt.Dimension;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.visitors.DotsLeftVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.ScoreTileVisitor;

public class Maze {
    private Tile[][] tiles;
    private final int width;
    private final int height;

    Maze(int width, int height, Tile[][] tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public boolean canMove(int x, int y) {
        return tileAt(x, y).isMovable();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return new Dimension(width * Tile.SIZE, height * Tile.SIZE);
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getScore() {
        ScoreTileVisitor scoreVisitor = new ScoreTileVisitor();
        int totalScore = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                totalScore += tiles[y][x].visit(scoreVisitor);
            }
        }
        return totalScore;
    }

    public boolean hasDotsLeft() {
        DotsLeftVisitor dotsLeftVisitor = new DotsLeftVisitor();
        int dotsLeft = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                dotsLeft += tiles[y][x].visit(dotsLeftVisitor);
            }
        }
        return dotsLeft > 0;
    }

    public Tile tileAt(int x, int y) {
        if (isValid(x, y)) {
            return tiles[y][x];
        } else {
            return new EmptyTile(new TileCoordinate(x, y));
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.append(tiles[y][x]);
            }
            result.append("\n");
        }

        return result.toString();
    }
}
