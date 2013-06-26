package com.thoughtworks.pacman.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static ImageLoader instance = new ImageLoader();

    public static Image loadImage(Class<?> className, String imageName) {
        return instance.load(className, imageName);
    }

    private Map<String, Image> images;

    private ImageLoader() {
        images = new HashMap<String, Image>();
    }
    
    private Image load(Class<?> resourceLocation, String imageName) {
        if (!images.containsKey(imageName)) {
            Image image = Toolkit.getDefaultToolkit().getImage(resourceLocation.getResource(imageName));
            images.put(imageName, image);
        }
        return images.get(imageName);
    }
}
