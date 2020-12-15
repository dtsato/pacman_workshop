  package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.tiles.FreezingItemBomb;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class FreezingItemBombPresenter implements Presenter {
    private static final int SIDE = 12;
    private final FreezingItemBomb item;
    private final Rectangle bounds;

    public FreezingItemBombPresenter(FreezingItemBomb freezingItem) {
        this.item = freezingItem;
        this.bounds = getBounds();
    }

    public void draw(Graphics2D graphics) {
        if (!item.isEaten()) {
            graphics.setColor(Color.blue);
            graphics.fill(bounds);
        }
    }

    public Rectangle getBounds() {
        int delta = SIDE / 2;
        Point upperLeft = item.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
        return new Rectangle(upperLeft.x, upperLeft.y, SIDE, SIDE);
    }
}