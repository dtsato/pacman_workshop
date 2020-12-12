package com.thoughtworks.pacman.ui.screens;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.BackgroundSoundLoader;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.Lock;

public class GameScreen implements Screen {
    private  ReentrantLock lock = new ReentrantLock();
    private final Game game;
    private final GamePresenter gamePresenter;
    private long lastFrameAt;
    private BackgroundSoundLoader BackgroundSoundLoader = new BackgroundSoundLoader();
    private Thread threadSounds = new Thread(BackgroundSoundLoader, "BackgroundSoundLoader");
    private boolean check = true ;

    public GameScreen() throws Exception {
        this(new Game());
    }

    private GameScreen(Game game) {
        this(game, new GamePresenter(game));
    }

    GameScreen(Game game, GamePresenter gamePresenter) {
        this.game = game;
        this.gamePresenter = gamePresenter;
        this.lastFrameAt = System.currentTimeMillis();
    }

    public void draw(Graphics2D graphics) {
        long currentFrameAt = System.currentTimeMillis();
        long timeDelta = currentFrameAt - lastFrameAt;

        game.advance(timeDelta);
        gamePresenter.draw(graphics);

        lastFrameAt = currentFrameAt;
    }

    public Screen getNextScreen() {
        if (game.won()) {
            return new WinScreen(game);
        } else if (game.lost() && !gamePresenter.isDying()) {
            return new LostScreen(game);
        }
        return this;
    }

    public void check(){
        if (game.won() && check) {
            check = false ;
            BackgroundSoundLoader.setStop();
        } else if ( check && game.lost() && !gamePresenter.isDying()) {
            check = false ;
            BackgroundSoundLoader.setStop();
        }
    }
    public void play (){
    try{   
            lock.lock();
            threadSounds.start();
            lock.unlock();
     }catch (Exception e) {
        }

    }
    
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            game.getPacman().setNextDirection(Direction.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            game.getPacman().setNextDirection(Direction.RIGHT);
            break;
        case KeyEvent.VK_UP:
            game.getPacman().setNextDirection(Direction.UP);
            break;
        case KeyEvent.VK_DOWN:
            game.getPacman().setNextDirection(Direction.DOWN);
            break;
        }
    }
}
