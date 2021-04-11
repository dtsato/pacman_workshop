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
    private int xOffSet;

    public GhostPresenter(Ghost ghost) {
        this.ghost = ghost;
        this.images = new Image[] { ImageLoader.loadImage(Presenter.class, ghost.getType().getImageOne()),
                ImageLoader.loadImage(Presenter.class, ghost.getType().getImageTwo()) };
    }

    public GhostPresenter(Ghost ghost, int xOffSet){
        this.ghost = ghost;
        this.xOffSet = xOffSet;
        this.images = new Image[] { ImageLoader.loadImage(Presenter.class, ghost.getType().getImageOne()),
                ImageLoader.loadImage(Presenter.class, ghost.getType().getImageTwo()) };     
    }

    public void draw(Graphics2D graphics) {
        Rectangle bounds = getBounds();
        Image image = lastFrame++ % 10 < 5 ? images[0] : images[1];
        graphics.drawImage(image, bounds.x + xOffSet, bounds.y, bounds.width, bounds.height, Color.black, null);
    }

    public Rectangle getBounds() {
        int radius = DIMENSION / 2;
        SpacialCoordinate upperLeft = ghost.getCenter().add(new SpacialCoordinate(-radius, -radius));
        return new Rectangle(upperLeft.toPoint(), new Dimension(DIMENSION, DIMENSION));
    }
}
