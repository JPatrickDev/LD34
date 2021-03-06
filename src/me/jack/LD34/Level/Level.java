package me.jack.LD34.Level;

import me.jack.LD34.Level.Tiles.*;
import me.jack.LD34.States.InGameState;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.w3c.dom.css.Rect;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Jack on 12/12/2015.
 */
public class Level {

    public int d;

    private Tile[] tiles;

    public Player player;

    private Point start, end;

    public int moves = 0,minMoves;

    ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();
    ArrayList<MoveableTile> moveableTiles = new ArrayList<MoveableTile>();

    public Level(int d) {
        this.d = d;
        tiles = new Tile[d * d];
        for (int i = 0; i != d * d; i++) {
            tiles[i] = new BasicTile(AllowedMovementType.DOWN_LEFT);
        }
    }

    org.newdawn.slick.Image exit;

    int cX = -1, cY = -1;

    public void render(Graphics g) {
        if (exit == null)
            exit = ImageUtil.loadImage("res/exit.png");
        //g.scale(2f,2f);
        if (cX == -1 && cY == -1) {
            int mW = d * Tile.tileSize;
            int mH = d * Tile.tileSize;
            cX = 200 - (mW / 2);
            cY = 160 - (mH / 2);
        }
        g.translate(cX,cY);
        for(int i = 0;i!= d +2;i++) {
            g.drawImage(Tile.border, (i * Tile.tileSize) - Tile.tileSize, -Tile.tileSize);
            g.drawImage(Tile.border, (i * Tile.tileSize) - Tile.tileSize, (d * Tile.tileSize));
        }

        for(int i = 0;i!= d;i++) {
            g.drawImage(Tile.border,-Tile.tileSize,i*Tile.tileSize);
            g.drawImage(Tile.border,d*Tile.tileSize,i*Tile.tileSize);
        }

        for (int x = 0; x != d; x++) {
            for (int y = 0; y != d; y++) {
                tiles[x + y * d].render(g, x, y);
            }
        }

        for (MoveableTile t : moveableTiles)
            t.render(g, 0, 0);

        g.drawImage(exit, end.x * Tile.tileSize, end.y * Tile.tileSize);
        player.render(g);
        g.resetTransform();
        //   g.resetTransform();
    }

    public void update(InGameState parent) {
        if (player.getX() / Tile.tileSize == end.x && player.getY() / Tile.tileSize == end.y) {
           // parent.nextLevel();
            parent.levelOver();
            SoundEngine.getInstance().play("end");
        }
        for (MoveableTile t : moveableTiles)
            t.update(this);
        player.update(this);
    }

    public boolean canMove(int nX, int nY, int oX, int oY) {
        if (nY < 0) {
            return false;
        }
        if (nY > (d - 1) * Tile.tileSize) {
            return false;
        }
        if (nX < 0) {
            return false;
        }
        if (nX > (d - 1) * Tile.tileSize) {
            return false;
        }

        Rectangle hitbox = new Rectangle(nX, nY, Tile.tileSize, Tile.tileSize);
        for (Rectangle rt : hitboxes)
            if (hitbox.intersects(rt))
                return false;

        for (MoveableTile t : moveableTiles) {
            Rectangle mtR = new Rectangle(t.x, t.y, Tile.tileSize, Tile.tileSize);
            if (mtR.intersects(hitbox)) {
                int dir = -1;
                if (nX > oX)
                    dir = 2;
                else if (nX < oX)
                    dir = 0;
                else if (nY > oY) {
                    dir = 3;
                } else if (nY < oY)
                    dir = 1;
                t.move(dir);
                return false;
            }
        }
        return true;
    }

    public static Level load(String path,boolean levelSelect) throws IOException {
        System.out.println(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Level.class.getResourceAsStream(path)));
        int d = Integer.parseInt(reader.readLine());
        String[] startSplit = reader.readLine().split(":");
        String[] endSplit = reader.readLine().split(":");
        Point start = new Point(Integer.parseInt(startSplit[0]), Integer.parseInt(startSplit[1]));
        Point end = new Point(Integer.parseInt(endSplit[0]), Integer.parseInt(endSplit[1]));
        int i = 0;
        Tile[] tiles = new Tile[d * d];
        int minMoves = Integer.parseInt(reader.readLine());
        String line = "";

        int x = 0, y = 0;
        Level level = new Level(d);
        level.minMoves = minMoves;
        if(levelSelect)
            return level;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("#"))
                continue;
            if (line.equals(""))
                continue;
            String[] split = line.split(":");
            int type = Integer.parseInt(split[0]);
            int move = Integer.parseInt(split[1]);
            TileType tType = TileType.values()[type];
            Tile t = null;
            switch (tType) {
                case BASIC:
                    t = new BasicTile(AllowedMovementType.values()[move]);
                    try{
                        int ii = Integer.parseInt(split[2]);
                        if(ii == 1)
                            level.moveableTiles.add(new MoveableTile(x*Tile.tileSize,y*Tile.tileSize));
                    }catch(Exception e){}
                    break;
                case FLING:
                    t = new FlingTile(Integer.parseInt(split[2]));
                    break;
                case TP:
                    t = new TPTile(new Point(Integer.parseInt(split[2]), Integer.parseInt(split[3])), Color.decode(split[4]));
                    try{
                        int ii = Integer.parseInt(split[5]);
                        if(ii == 1)
                            level.moveableTiles.add(new MoveableTile(x*Tile.tileSize,y*Tile.tileSize));
                    }catch(Exception e){}break;
                case TPTARGET:
                    t = new TPTargetTile(AllowedMovementType.values()[move], Color.decode(split[2]));
                    break;

                case WALL:
                    t = new WallTile();
                    level.hitboxes.add(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize));
                    break;
                case MOVEABLE:
                    t = new BasicTile(AllowedMovementType.values()[move]);
                    MoveableTile tt = new MoveableTile(x * Tile.tileSize, y * Tile.tileSize);
                    level.moveableTiles.add(tt);
                    //  level.hitboxes.add(new Rectangle(x*Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize));
                    break;

            }
            tiles[i] = t;
            i++;
            x++;
            if (x >= d) {
                x = 0;
                y++;
            }
        }


        level.setTiles(tiles);
        level.setStartEnd(start, end);
        return level;
    }

    public void playerMoved(int nX, int nY) {
        tiles[(nX / Tile.tileSize) + (nY / Tile.tileSize) * d].steppedOn(this);
    }

    private void setStartEnd(Point start, Point end) {
        this.start = start;
        this.end = end;
        player = new Player(start.x * Tile.tileSize, start.y * Tile.tileSize);
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }


    public AllowedMovementType currentMove() {
        return tiles[(player.getX() / Tile.tileSize) + (player.getY() / Tile.tileSize) * d].getMoves();
    }

    public void tpPlayer(Point target) {
        player.setX(target.x * Tile.tileSize);
        player.setY(target.y * Tile.tileSize);
        player.tped();
        SoundEngine.getInstance().play("tp");
    }
}
