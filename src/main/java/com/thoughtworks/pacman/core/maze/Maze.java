package com.thoughtworks.pacman.core.maze;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.FreezingItem;
import com.thoughtworks.pacman.core.tiles.FreezingItemBomb;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.visitors.DotsLeftVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.ScoreTileVisitor;

public class Maze {
    private final Map<TileCoordinate, Tile> tiles;
    private final int width;
    private final int height;
    
    private TileCoordinate freezingItemCoordinate;
    private int freezingItemCount = 0;
    private Tile oldTile;
    private FreezingItem aliveFreezingItem;

    private ArrayList<TileCoordinate> aliveFreezingItemBombs = new ArrayList<>();

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

    public Dimension getDimension() {
        return new Dimension(width * Tile.SIZE, height * Tile.SIZE);
    }

    public FreezingItem getAliveItem() {
        return aliveFreezingItem;
    }

    public ArrayList<TileCoordinate> getAliveFreezingItemBombs() {
        return aliveFreezingItemBombs;
    }

    public void setFreezingItemCount(int freezingItemCount) {
        this.freezingItemCount = freezingItemCount;
    }

    public void insertFreezingItem(TileCoordinate ItemCoordinate){
        this.aliveFreezingItem = new FreezingItem(ItemCoordinate);
        this.freezingItemCoordinate = ItemCoordinate;
        oldTile=this.tiles.get(freezingItemCoordinate);
        this.tiles.put(ItemCoordinate, this.aliveFreezingItem); 
    }

    
    public void insertFreezingItemBomb(TileCoordinate ItemCoordinate){
        if(freezingItemCount<=0)
            return;

        this.aliveFreezingItemBombs.add(ItemCoordinate);
        this.tiles.put(ItemCoordinate, new FreezingItemBomb(ItemCoordinate)); 
        freezingItemCount--;
    }

    public TileCoordinate getFreezingItemCoordinate(){
        return this.freezingItemCoordinate;
    }

    public boolean isFreezingItemAlive(){
        return this.aliveFreezingItem != null;
    }

    public void removeFreezingItem(){
        this.tiles.replace(this.freezingItemCoordinate, this.oldTile);
        this.freezingItemCoordinate = null;
        this.aliveFreezingItem = null;
    }

    public void removeFreezingItemBomb(TileCoordinate ItemCoordinate){
        this.aliveFreezingItemBombs.remove(ItemCoordinate);
        this.tiles.put(ItemCoordinate, new EmptyTile(ItemCoordinate)); 
    }

    public void eatFreezingItem(){
        if(this.aliveFreezingItem == null)
            return;

        this.aliveFreezingItem.eat();
        this.freezingItemCount++;
        this.removeFreezingItem();
    }

    public int getFreezingItemCount(){
        return this.freezingItemCount;
    }

    public int getScore() {
        ScoreTileVisitor scoreVisitor = new ScoreTileVisitor();
        int totalScore = 0;
        for (Tile tile : tiles.values()) {
            totalScore += tile.visit(scoreVisitor);
        }
        return totalScore;
    }

    public boolean hasDotsLeft() {
        DotsLeftVisitor dotsLeftVisitor = new DotsLeftVisitor();
        int dotsLeft = 0;
        for (Tile tile : tiles.values()) {
            dotsLeft += tile.visit(dotsLeftVisitor);
        }
        return dotsLeft > 0;
    }

    public Tile tileAt(TileCoordinate tileCoordinate) {
        if (tiles.containsKey(tileCoordinate)) {
            return tiles.get(tileCoordinate);
        } else {
            return new EmptyTile(tileCoordinate);
        }
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.append(tileAt(new TileCoordinate(x, y)));
            }
            result.append("\n");
        }

        return result.toString();
    }
}
