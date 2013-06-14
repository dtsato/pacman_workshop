package com.thoughtworks.pacman.core.maze;

import java.util.Collection;
import java.util.Map;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;

public class Maze {
    private Map<TileCoordinate, Tile> tiles;
    private final int width;
    private final int height;

    Maze(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public boolean canMove(TileCoordinate tileCoordinate) {
        return tileAt(tileCoordinate).isMovable();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile tileAt(TileCoordinate tileCoordinate) {
        return this.tiles.get(tileCoordinate);
    }

    public Collection<Tile> getTiles() {
        return tiles.values();
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.append(tiles.get(new TileCoordinate(x, y)));
            }
            result.append("\n");
        }

        return result.toString();
    }
}
