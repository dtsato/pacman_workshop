package com.thoughtworks.pacman.core;

import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Maze {
    private static final String MAZE_MAP_FILENAME = "maze.map";
    private static final Map<Character, Class<? extends Tile>> mazeParser = new HashMap<Character, Class<? extends Tile>>() {{
        put('+', Wall.class);
        put('-', Wall.class);
        put('.', Dot.class);
        put('*', Dot.class);
        put(' ', EmptyTile.class);
    }};

    private Tile[][] tiles;
    private final int width;
    private final int height;

    public Maze() throws Exception {
        this(MAZE_MAP_FILENAME);
    }

    Maze(String filename) throws Exception {
        final InputStream fileInputStream = this.getClass().getResourceAsStream(filename);
        final Scanner scanner = new Scanner(fileInputStream);

        try {
            width = scanner.nextInt();
            height = scanner.nextInt();
            tiles = new Tile[width][height];
            scanner.nextLine();
            for (int j = 0; j < height; j++) {
                final String line = scanner.nextLine();
                for (int i = 0; i < width; i++) {
                    final char mazeTileCharacter = line.charAt(i);
                    tiles[i][j] = mazeParser.get(mazeTileCharacter).getConstructor().newInstance();
                }
            }
        } finally {
            scanner.close();
        }
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
        return this.tiles[tileCoordinate.x][tileCoordinate.y];
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                result.append(tiles[i][j]);
            }
            result.append("\n");
        }

        return result.toString();
    }
}
