package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameRunner {
    private static final int FRAME_INTERVAL = 30;

    public static void main(String[] args) throws Exception {
        GameRunner runner = new GameRunner();
        runner.initialize();
        runner.run();
    }

    private boolean open;
    private GameCanvas canvas;
    private Game game;

    private void initialize() throws Exception {
        game = new Game();
        canvas = new GameCanvas(game);
        GameController controller = new GameController(game);

        JFrame container = new JFrame();

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(canvas.getDimension());
        panel.setLayout(null);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);
        open = true;

        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                open = false;
                System.exit(0);
            }
        });

        canvas.initialize(panel);
        controller.initialize(canvas);
    }

    private void run() {
        long lastFrameAt = System.currentTimeMillis();

        while (open) {
            long currentFrameAt = System.currentTimeMillis();
            long timeDelta = currentFrameAt - lastFrameAt;

            game.advance(timeDelta);
            canvas.draw();

            lastFrameAt = currentFrameAt;

            try {
                Thread.sleep(FRAME_INTERVAL);
            } catch (InterruptedException e) {
            }
        }
    }
}
