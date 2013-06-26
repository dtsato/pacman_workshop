package com.thoughtworks.pacman.ui.presenters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Presenter;

public class GhostPresenter implements Presenter {
    private static final int DIMENSION = 20;
    private final Ghost ghost;
    private final Image[] images;
    private long lastFrame;

    public GhostPresenter(Ghost ghost) {
        this.ghost = ghost;
        this.images = new Image[] { ImageLoader.loadImage(ghost.getType().getImageOne()),
                ImageLoader.loadImage(ghost.getType().getImageTwo()) };
    }

    public void draw(Graphics2D graphics) {
        Rectangle bounds = getBounds();
        Image image = lastFrame++ % 10 < 5 ? images[0] : images[1];
        graphics.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, Color.black, null);
    }

    public Rectangle getBounds() {
        int radius = DIMENSION / 2;
        SpacialCoordinate upperLeft = ghost.getCenter().add(new SpacialCoordinate(-radius, -radius));
        return new Rectangle(upperLeft.toPoint(), new Dimension(DIMENSION, DIMENSION));
    }
}
