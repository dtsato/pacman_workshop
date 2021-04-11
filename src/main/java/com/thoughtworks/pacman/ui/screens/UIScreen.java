package com.thoughtworks.pacman.ui.screens;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.Color;



import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;
import com.thoughtworks.pacman.ui.presenters.GamePresenter;


public class UIScreen extends JFrame  implements Screen,ActionListener{

    private final Game game;
    private final GamePresenter gamePresenter;
 
    private boolean startGame;
    private boolean resumeGame;
  

    static final Image BACKGROUND_IMAGE = ImageLoader.loadImage(Screen.class, "Background.png");
    static final Image START_IMAGE = ImageLoader.loadImage(Screen.class, "StartButton.png");

    static final Image RESUME_IMAGE = ImageLoader.loadImage(Screen.class, "ResumeButton.png");
    static final Image SETTINGS_IMAGE = ImageLoader.loadImage(Screen.class, "Settings.png");
    static final Image EXIT_IMAGE = ImageLoader.loadImage(Screen.class, "ExitButton.png");
 
    private static final int WIDTH = 350;
    private static final int HEIGHT = 550;

    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 60;
    
    private JButton startButton = new JButton("START");
    private JButton resumeButton = new JButton("RESUME");
    private JButton exitButton = new JButton("EXIT");
    private JButton scoreLabel = new JButton("SCORE");
    private JButton settingsButton = new JButton("SETTINGS");


    public UIScreen(Game game,GamePresenter gamePresenter) {
      
        this.gamePresenter= gamePresenter;
        this.game = game;
        this.startGame = false;
        resumeGame=false;
        setSize(WIDTH, HEIGHT);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(new JLabel(new ImageIcon("pacman_workshop\\src\\main\\resources\\com\\thoughtworks\\pacman\\ui\\Background.png") ) );
        placeButtons();
        this.add(startButton);
        this.add(resumeButton);
        this.add(exitButton);
        this.add(scoreLabel);
        this.add(settingsButton);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
     
        setLayout(null);
        setVisible(true);
        setLocation(450,0);
        toFront();
    
    }
    public UIScreen() throws Exception {
        this.game = new Game();
       
        this.gamePresenter=new GamePresenter(game);
        this.startGame = false;
        resumeGame=false;
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(new JLabel(new ImageIcon("pacman_workshop\\src\\main\\resources\\com\\thoughtworks\\pacman\\ui\\Background.png") ) );
        placeButtons();
        this.add(scoreLabel);
        this.add(startButton);
        this.add(resumeButton);
        this.add(exitButton);
        this.add(settingsButton);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
     
        setLayout(null);
        setVisible(true);
        setLocation(450,0);
        toFront();
    
    }
 

    @Override
    public void draw(Graphics2D graphics) {
        long timeDelta = 0;

        game.advance(timeDelta);
        gamePresenter.draw(graphics);

    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            //ESCAPE ile Oyuna geri doner!
            case KeyEvent.VK_ESCAPE:
                resumeGame= true;
                break;
           
            }
        
    }


    @Override
    public Screen getNextScreen() throws Exception {
        if (resumeGame) {
            return new GameScreen(game,gamePresenter);
        }
        if (startGame) {
            return new GameScreen();
        }
      
       
        return this;
    }

   
   
    private void placeButtons() {
       
        startButton.setIcon(new ImageIcon("pacman_workshop\\src\\main\\resources\\com\\thoughtworks\\pacman\\ui\\StartButton.png"));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setContentAreaFilled(false);
        startButton.setFocusable(false);

        resumeButton.setIcon(new ImageIcon("pacman_workshop\\src\\main\\resources\\com\\thoughtworks\\pacman\\ui\\ResumeButton.png"));
        resumeButton.setBorder(BorderFactory.createEmptyBorder());
        resumeButton.setContentAreaFilled(false);
        resumeButton.setFocusable(false);

        settingsButton.setIcon(new ImageIcon("pacman_workshop\\src\\main\\resources\\com\\thoughtworks\\pacman\\ui\\Settings.png"));
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusable(false);

        exitButton.setIcon(new ImageIcon("pacman_workshop\\src\\main\\resources\\com\\thoughtworks\\pacman\\ui\\ExitButton.png"));
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusable(false);
        
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setText(String.format("SCORE: %2d", game.getMaze().getScore()) );
        scoreLabel.setBorder(BorderFactory.createEmptyBorder());
        scoreLabel.setContentAreaFilled(false);
        scoreLabel.setFocusable(false);

        startButton.setBounds(20, 70, BUTTON_WIDTH, BUTTON_HEIGHT);
        resumeButton.setBounds(20, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
        settingsButton.setBounds(20, 230, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton.setBounds(20, 310, BUTTON_WIDTH, BUTTON_HEIGHT);
        scoreLabel.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);


        startButton.addActionListener(this);
        resumeButton.addActionListener(this);
        settingsButton.addActionListener(this);
        exitButton.addActionListener(this);
       
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       String command = e.getActionCommand();
        if (command.equals("START")) {
            startGame=true;
            super.dispose();
        }
        if (command.equals("RESUME")) {
            resumeGame=true;
            super.dispose();
        }
        if (command.equals("EXIT")) {
            System.exit(0);
        }
        if (command.equals("SETTINGS")) {
            game.getPacman().setSpeed(250);
            
        }
      
        
    }



  

    

}

