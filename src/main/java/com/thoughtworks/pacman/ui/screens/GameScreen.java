package com.thoughtworks.pacman.ui.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;

public class GameScreen implements Screen {
    private final Game game;
    private final GamePresenter gamePresenter;
    private long lastFrameAt;
    
    public boolean menuCheck=false;

    public GameScreen() throws Exception {
        this(new Game());
    }

    GameScreen(Game game) {
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
        if(menuCheck){
            return new UIScreen(game,gamePresenter);

        }
        return this;
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
        case KeyEvent.VK_ESCAPE:
            //ESCye basilinca oyun duruyor ve menu aciliyor
            
            menuCheck=true;
            break;
            
        }

    }

   
    


}
