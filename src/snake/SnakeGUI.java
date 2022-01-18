/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author BISOLA-OJO
 */
public class SnakeGUI extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private Image background;
    private Graphics grphcs;
    private GameEngine gameArea;
    
    public SnakeGUI(){
        setTitle("Snake");
        setSize(850,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start();
    };
    void start(){
        //grphcs.drawImage(background, 0, 0, 800, 600, null);
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();
        JButton start = new JButton();
        start.setText("Start");
        start.addActionListener(getActionListener(1));
        JButton high = new JButton();
        high.setText("HighScores");
        high.addActionListener(getActionListener(2));
        JButton level = new JButton();
        level.setText("Set Level");
        level.addActionListener(getActionListener(3));
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        getContentPane().add(start);
        getContentPane().add(high);
        getContentPane().add(level);
    }
       private ActionListener getActionListener(final int size){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();
                //load game engine or high score or set level
                /*experiment*/
                if (size == 1){
                gameArea = new GameEngine(1);
                getContentPane().add(gameArea);
                }
                if (size == 3){
              for (int amount : new Integer[]{1,2,3,4,5,6,7,8,9,10}) {
            JButton button = new JButton(String.valueOf(amount));
            button.addActionListener(new LevelLoader(amount));
            getContentPane().add(button);
        }
                }
            if (size == 2){
                    try {
                        HighScores h = new HighScores(10);
                        for (HighScore t: h.getHighScores()){
                            JLabel m = new JLabel("M");
                            m.setText(t.toString());
                            getContentPane().add(m);
                        }   } catch (SQLException ex) {
                        Logger.getLogger(SnakeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
        };
                }
        class LevelLoader implements ActionListener {

        private final int amount;

        public LevelLoader(int amount) {
            this.amount = amount;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            getContentPane().removeAll();
                getContentPane().revalidate();
                getContentPane().repaint();
            gameArea = new GameEngine(amount);
            getContentPane().add(gameArea);
                }
        }
        
}
