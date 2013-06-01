package com.thoughtworks.pacman.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameRunner {
    public static void main(String[] args) {
        GameRunner runner = new GameRunner();
        runner.initialize();
        runner.run();
    }

    private GameCanvas canvas;

    private void initialize() {
        JFrame container = new JFrame();

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setLayout(null);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        canvas = new GameCanvas();
        canvas.initialize(panel);
    }

    private void run() {
        while (true) {
            canvas.draw();
        }
    }
}
