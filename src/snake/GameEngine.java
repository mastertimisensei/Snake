/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
//import java.io.IOException;
//import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author BISOLA-OJO
 */

public class GameEngine extends JPanel {
    private final int FPS = 240;
    private final int SNAKE_MOVEMENT = 1;
    private final int BALL_RADIUS = 20;    

    private boolean paused = false;
    private Image background;
    private int levelNum;
    public int score;
    private Timer newFrameTimer;
    private RattleSnake snake;
    private Ball ball;
    private Food food;
    private ArrayList<Obstacle> obstacle;
    private HighScores h;

        public GameEngine(int level) {
        super();
        try{
        h = new HighScores(10);
        }catch(SQLException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        score = 0;
        levelNum = level;
        background = new ImageIcon("data/background.jpg").getImage();
        //snake.setVelx(SNAKE_MOVEMENT);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snake.setVelx(-SNAKE_MOVEMENT);
                snake.setVely(0);
            }
        });
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snake.setVelx(SNAKE_MOVEMENT);
                snake.setVely(0);
            }
        });
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snake.setVelx(0);
                snake.setVely(SNAKE_MOVEMENT);
            }
        });
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                snake.setVelx(0);
                snake.setVely(-SNAKE_MOVEMENT);
            }
        });
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        }
       @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 800, 600, null);
        snake.draw((Graphics2D) grphcs);
        food.draw(grphcs);
        for (Obstacle o: obstacle){
            o.draw(grphcs);
        }
    }
    void restart(){
        
        snake = new RattleSnake();
        food = new Food((int) (Math.random()*790), (int) (Math.random() *590), 20,20, new ImageIcon("data/997087-middle.png").getImage() );
        obstacle = new ArrayList();
        for (int i = 0; i < levelNum; i++) {
                    obstacle.add(new Obstacle((int) (Math.random()*790), (int) (Math.random() *590), 30,20, new ImageIcon("data/rock.png").getImage()));
                }
    }
    class NewFrameListener implements ActionListener {
         @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
            snake.moveX();
            snake.moveY();
            if (snake.x >= 800 || snake.x <= 0 || snake.y <= 0 || snake.y >= 600){
                String result = (String)JOptionPane.showInputDialog(
                new JFrame(),
               "Enter Your name", 
               "Score",            
               JOptionPane.PLAIN_MESSAGE,
               null,            
               null, 
               "");
                try {
                    h.putHighScore(result, score);
                } catch (SQLException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
                restart();
            }
            if (snake.eats(food)){
                food = new Food((int) (Math.random()*800), (int) (Math.random() *600), 20,20, new ImageIcon("data/997087-middle.png").getImage() );
                snake.IncreaseRec();
                score++;
            }
            for (Obstacle o : obstacle){
                if (snake.collides(o)){
                      String result = (String)JOptionPane.showInputDialog(
                new JFrame(),
               "Enter Your name", 
               "Score",            
               JOptionPane.PLAIN_MESSAGE,
               null,            
               null, 
               "");
                try {
                    h.putHighScore(result, score);
                } catch (SQLException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
                    restart();
                }
            }
            //ball.moveX();
            
            //revalidate();
            repaint();
            }
            }
    }
}
