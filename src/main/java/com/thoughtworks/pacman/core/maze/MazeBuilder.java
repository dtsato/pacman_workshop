package com.thoughtworks.pacman.core.maze;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Wall;

public class MazeBuilder {
    private int width = 0;
    private int height = 0;
    private Map<TileCoordinate, Tile> tiles = new HashMap<>();

    Maze build() {
        return new Maze(width, height, tiles);
    }

    void process(String row) {
        String[] split = row.split("");
        for (int x = 0; x < split.length; x++) {
            TileCoordinate coordinate = new TileCoordinate(x, height);
            tiles.put(coordinate, new Wall(coordinate));
        }
        width = row.length();
        height += 1;
    }
}
