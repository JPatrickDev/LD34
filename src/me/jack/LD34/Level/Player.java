package me.jack.LD34.Level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

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
}
