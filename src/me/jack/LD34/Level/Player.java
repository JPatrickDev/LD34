package me.jack.LD34.Level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.awt.*;

/**
 * Created by Jack on 12/12/2015.
 */
public class Player {

    private int x, y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(x, y, Tile.tileSize, Tile.tileSize);
        g.setColor(Color.white);
    }

    //0 = standing, 1 = moving
    private Point movingTo = null;
    public int state = 0;
    public void update(Level level){
        if(movingTo != null && state == 1){
            int tX = movingTo.x;
            int tY = movingTo.y;
            if(tX > x){
                x++;
            }else if(tX < x){
                x--;
            }else if(tY > y){
                y++;
            }else{
                y--;
            }

            if(tX == x && tY == y){
                movingTo = null;
                state = 0;
                level.playerMoved(x,y);
            }

        }
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveTo(int x, int y) {
        movingTo = new Point(x,y);
        state = 1;
    }
}
