package me.jack.LD34.Level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Sound;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

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

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, Tile.tileSize, Tile.tileSize);
        g.setColor(Color.white);
    }

    //0 = standing, 1 = moving
    private Point movingTo = null;
    public int state = 0;
    boolean fling = false;
    int fDir = -1;

    public void fling(int dir) {
        fling = true;
        fDir = dir;
        setFTarget();
    }

    private void setFTarget() {
        if (fDir == 0) {
            movingTo = new Point(x - Tile.tileSize, y);
        } else if (fDir == 1) {
            movingTo = new Point(x, y - Tile.tileSize);
        } else if (fDir == 2) {
            movingTo = new Point(x + Tile.tileSize, y);
        } else {
            movingTo = new Point(x, y + Tile.tileSize);
        }
        state = 1;
    }

    public void update(Level level) {
        if (movingTo != null && state == 1) {
            int tX = movingTo.x;
            int tY = movingTo.y;
            if (tX > x) {
                if(level.canMove(x+2,y,x,y))
                x += 2;
                else {
                    state = 0;
                    movingTo = null;
                    fling = false;
                    fDir = -1;
                    SoundEngine.getInstance().play("badmove");
                }
            } else if (tX < x) {
                if(level.canMove(x-2,y,x,y))
                x -= 2;
                else {
                    state = 0;
                    movingTo = null;
                    fling = false;
                    fDir = -1;
                    SoundEngine.getInstance().play("badmove");
                }
            } else if (tY > y) {
                if(level.canMove(x,y+2,x,y))
                y += 2;
                else {
                    state = 0;
                    movingTo = null;
                    fling = false;
                    fDir = -1;
                    SoundEngine.getInstance().play("badmove");
                }
            } else {
                if(level.canMove(x,y-2,x,y))
                y -= 2;
                else {
                    state = 0;
                    movingTo = null;
                    fling = false;
                    fDir = -1;
                    SoundEngine.getInstance().play("badmove");
                }
            }

            if (tX == x && tY == y) {
                movingTo = null;
                state = 0;
                level.playerMoved(x, y);
                level.moves++;
                if (fling) {
                    setFTarget();
                }
            }

        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveTo(int x, int y) {
        movingTo = new Point(x, y);
        state = 1;
    }
}
