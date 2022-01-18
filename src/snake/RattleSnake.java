/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 *
 * @author BISOLA-OJO
 */

public class RattleSnake /*extends Sprite*/ {
        private double velx;
        private double vely;
        private boolean drawn = false;
        private ArrayList<Integer> DotX; 
        private ArrayList<Integer> DotY;
        public int x;
        public int y;
        private Color color;
        public int tail=2;
        
        public RattleSnake(){
            x = 400;
            y = 300;
            DotX = new ArrayList<>();
            DotY = new ArrayList<>();
            DotX.add(x);
            DotY.add(y);
            DotX.add(x-10);
            DotY.add(y);
        }
        /*public RattleSnake(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }*/

        
       
        
        public void draw(Graphics2D g2) {
        g2.setColor(new Color(1,1,0));
        for (int i = 0; i < tail; i++){
        g2.fillOval(DotX.get(i), DotY.get(i), 10, 10);
        }
        
        
        }
        
        
        /*for (int i = 0; i < rectback; i++){
            if (true){
                g2.fillOval((int) otherX, (int) y, 10, 10);
                otherX= otherX-10;
            }else{
                g2.fillOval((int) x, (int) otherY, 10, 10);
                otherY=otherY - 10;
            }
        }*/
        //add rectangle to back  of snake
        
    //}
        public void moveX() {
        if ((velx < 0 && x > 0) || (velx > 0 && x <= 800)) {
            for (int i = 1; i < tail; i++){
                if (velx > 0){
                DotX.set(i,DotX.get(i-1)-8);
                DotY.set(i,DotY.get(i-1));}
                else{
                DotX.set(i,DotX.get(i-1)+8);
                DotY.set(i,DotY.get(i-1));}    
            }
            x += velx;
            DotX.set(0,x);
        }
        }
        public void moveY() {
        if ((vely < 0 && y > 0) || (vely > 0 && y <= 600)) {
            for (int i = 1; i < tail; i++){
                if (vely > 0){
                DotX.set(i,DotX.get(i-1));
                DotY.set(i,DotY.get(i-1)-8);}
                else{
                DotX.set(i,DotX.get(i-1));
                DotY.set(i,DotY.get(i-1)+8);} 
                }
            }
            y += vely;
            DotY.set(0,y);
        }
        
        
    public void setVelx(double v) {
        this.velx = v;
    }
    public void setVely(double v) {
        this.vely = v;
    }
    public double getVelx() {
        return velx;
    }
        
    public void IncreaseRec(){
        //if ((vely < 0 && y > 0) || (vely > 0 && y <= 600)) {
            DotX.add(DotX.get(tail-1));
            DotY.add(DotY.get(tail-1));
            tail+=1;
            /*for (int i = 1; i < tail-1; i++){
                if (vely > 0){
                DotX.set(i,DotX.get(i-1));
                DotY.set(i,DotY.get(i-1)-8);}
                else{
                DotX.set(i,DotX.get(i-1));
                DotY.set(i,DotY.get(i-1)+8);} 
                }
            }
            y += vely;
            DotY.set(0,y);*/
        
       }
    public boolean eats(Food other){
         return (DotX.get(0) >= other.getX() && DotY.get(0) >= other.getY() && DotX.get(0) <= (other.getX() + other.getWidth()) && DotY.get(0) <= other.getY()+ other.getHeight());
        //return true;
    }
    
    public boolean collides(Obstacle other){
         return (DotX.get(0) >= other.getX() && DotY.get(0) >= other.getY() && DotX.get(0) <= (other.getX() + other.getWidth()) && DotY.get(0) <= other.getY()+ other.getHeight());
        //return true;
    }
    /*private double velx;

    public RattleSnake(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }

    public void move() {
        if ((velx < 0 && x > 0) || (velx > 0 && x + width <= 800)) {
            x += velx;
        }
    }

    public double getVelx() {
        return velx;
    }

    public void setVelx(double velx) {
        this.velx = velx;
    }*/
}
