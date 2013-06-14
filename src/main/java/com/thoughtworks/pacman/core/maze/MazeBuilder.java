package com.thoughtworks.pacman.core.maze;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class MazeBuilder {
    private static final String MAZE_MAP_FILENAME = "maze.map";
    @SuppressWarnings("serial")
    private static final Map<Character, Class<? extends Tile>> mazeParser = new HashMap<Character, Class<? extends Tile>>() {
        {
            put('+', Wall.class);
            put('-', Wall.class);
            put('*', Dot.class);
            put('.', Dot.class);
            put(' ', EmptyTile.class);
        }
    };

    public static Maze buildDefaultMaze() throws Exception {
        InputStream fileInputStream = MazeBuilder.class.getResourceAsStream(MAZE_MAP_FILENAME);
        Scanner scanner = new Scanner(fileInputStream);

        MazeBuilder builder = new MazeBuilder();
        while (scanner.hasNextLine()) {
            builder.process(scanner.nextLine());
        }
        return builder.build();
    }

    private int width = 0;
    private int height = 0;
    private Map<TileCoordinate, Tile> tiles = new HashMap<>();

    Maze build() {
        return new Maze(width, height, tiles);
    }

    void process(String row) throws Exception {
        for (int x = 0; x < row.length(); x++) {
            TileCoordinate coordinate = new TileCoordinate(x, height);
            tiles.put(coordinate, createTile(row.charAt(x), coordinate));
        }
        width = row.length();
        height += 1;
    }

    private Tile createTile(char tileCharacter, TileCoordinate coordinate) throws Exception {
        return mazeParser.get(tileCharacter).getConstructor(TileCoordinate.class)
                .newInstance(coordinate);
    }
}
