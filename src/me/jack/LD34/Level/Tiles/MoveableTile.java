package me.jack.LD34.Level.Tiles;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import me.jack.LD34.Level.TileType;
import org.newdawn.slick.*;

import java.awt.*;

/**
 * Created by Jack on 12/12/2015.
 */
public class MoveableTile extends Tile {

    public MoveableTile(int x, int y) {
        super(TileType.MOVEABLE, AllowedMovementType.NONE);
        this.x = x;
        this.y = y;
    }

    Point movingTo;
    int state = 0;
    public int x;
    public int y;
    boolean moved = false;

    public void update(Level level) {
        if (movingTo != null && state ==  1&& !moved) {
            int tX = movingTo.x;
            int tY = movingTo.y;
            if (tX > x) {
                    x += 2;
            } else if (tX < x) {
                    x -= 2;
            } else if (tY > y) {
                    y += 2;
            } else {
                    y -= 2;
            }

            if (tX == x && tY == y) {
                movingTo = null;
                state = 0;
                moved = true;
                System.out.println("Moveable tile finished moving");
            }

        }
    }


    @Override
    public void render(org.newdawn.slick.Graphics g, int xx, int yy) {
        g.drawImage(moveableTile, x, y);
    }

    public void move(int dir) {
        if(moved)return;
        System.out.println("Moving");
        state = 1;
        if (dir == 0) {
            movingTo = new Point(x - Tile.tileSize, y);
        } else if (dir == 1) {
            movingTo = new Point(x, y - Tile.tileSize);
        } else if (dir == 2) {
            movingTo = new Point(x + Tile.tileSize, y);
        } else if (dir == 3) {
            movingTo = new Point(x, y + Tile.tileSize);
        }
    }

    @Override
    public void steppedOn(Level level) {

    }
}
