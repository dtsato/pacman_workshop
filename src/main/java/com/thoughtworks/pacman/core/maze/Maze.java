package com.thoughtworks.pacman.core.maze;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Maze {
    private static final String MAZE_MAP_FILENAME = "maze.map";
    @SuppressWarnings("serial")
	private static final Map<Character, Class<? extends Tile>> mazeParser = new HashMap<Character, Class<? extends Tile>>() {{
        put('+', Wall.class);
        put('-', Wall.class);
        put('.', Dot.class);
        put('*', Dot.class);
        put(' ', EmptyTile.class);
    }};

    private Map<TileCoordinate, Tile> tiles;
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
            tiles = new HashMap<TileCoordinate, Tile>(width * height);
            scanner.nextLine();
            for (int y = 0; y < height; y++) {
                final String line = scanner.nextLine();
                for (int x = 0; x < width; x++) {
                    final char mazeTileCharacter = line.charAt(x);
                    final TileCoordinate coordinate = new TileCoordinate(x, y);
                    tiles.put(coordinate, mazeParser.get(mazeTileCharacter).getConstructor(TileCoordinate.class).newInstance(coordinate));
                }
            }
        } finally {
            scanner.close();
        }
    }

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
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
