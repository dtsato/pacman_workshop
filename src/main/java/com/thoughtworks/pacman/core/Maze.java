package com.thoughtworks.pacman.core;

import java.io.InputStream;
import java.util.Arrays;
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
                    if (mazeParser.containsKey(mazeTileCharacter))
                        tiles[i][j] = mazeParser.get(mazeTileCharacter).getConstructor().newInstance();
                }
            }
        } finally {
            scanner.close();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile tileAt(int x, int y) {
        return this.tiles[x][y];
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (tiles[i][j] instanceof Wall)
                    result.append("+");
                else if (tiles[i][j] instanceof Dot)
                    result.append(".");
                else
                    result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
