package com.thoughtworks.pacman.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static ImageLoader instance = new ImageLoader();

    public static Image loadImage(String image) {
        return instance.load(image);
    }

    private Map<String, Image> images;

    private ImageLoader() {
        images = new HashMap<>();
    }
    
    private Image load(String imageName) {
        if (!images.containsKey(imageName)) {
            Image image = Toolkit.getDefaultToolkit().getImage(Presenter.class.getResource(imageName));
            images.put(imageName, image);
        }
        return images.get(imageName);
    }
}
