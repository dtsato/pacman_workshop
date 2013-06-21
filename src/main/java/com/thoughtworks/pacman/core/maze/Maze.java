package com.thoughtworks.pacman.core.maze;

import java.util.Collection;
import java.util.Map;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

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

    public Collection<Tile> getTiles() {
        return tiles.values();
    }

    public Tile tileAt(TileCoordinate tileCoordinate) {
        if (tiles.containsKey(tileCoordinate))
            return this.tiles.get(tileCoordinate);
        else
            return new EmptyTile(tileCoordinate);
    }

    public boolean canTeleport(TileCoordinate tileCoordinate, Direction direction) {
        return !tiles.containsKey(tileCoordinate) && tileAt(teleportedCoordinate(tileCoordinate, direction)).isMovable();
    }

    public TileCoordinate teleportedCoordinate(TileCoordinate tileCoordinate, Direction direction) {
        int oppositeSide = (direction == Direction.UP || direction == Direction.DOWN) ? height : width;
        return tileCoordinate.subtract(direction.tileDelta().times(oppositeSide));
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
