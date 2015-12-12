package me.jack.LD34.Level;

import me.jack.LD34.Level.Tiles.BasicTile;
import me.jack.LD34.Level.Tiles.FlingTile;
import me.jack.LD34.States.InGameState;
import org.newdawn.slick.Graphics;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Jack on 12/12/2015.
 */
public class Level {

    public int d;

    private Tile[] tiles;

    public Player player;

    private Point start, end;

    public int moves = 0;

    public Level(int d) {
        this.d = d;
        tiles = new Tile[d * d];
        for (int i = 0; i != d * d; i++) {
            tiles[i] = new BasicTile(AllowedMovementType.DOWN_LEFT);
        }
    }


    public void render(Graphics g) {
        for (int x = 0; x != d; x++) {
            for (int y = 0; y != d; y++) {
                tiles[x + y * d].render(g, x, y);
            }
        }

        g.fillRect(start.x * Tile.tileSize, start.y * Tile.tileSize, Tile.tileSize, Tile.tileSize);
        g.fillRect(end.x * Tile.tileSize, end.y * Tile.tileSize, Tile.tileSize, Tile.tileSize);

        player.render(g);
    }

    public void update(InGameState parent){
        if(player.getX() / Tile.tileSize == end.x && player.getY() / Tile.tileSize == end.y){
            parent.nextLevel();
        }
        player.update(this);
    }

    public static Level load(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        int d = Integer.parseInt(reader.readLine());
        String[] startSplit = reader.readLine().split(":");
        String[] endSplit = reader.readLine().split(":");
        Point start = new Point(Integer.parseInt(startSplit[0]), Integer.parseInt(startSplit[1]));
        Point end = new Point(Integer.parseInt(endSplit[0]), Integer.parseInt(endSplit[1]));
        int i = 0;
        Tile[] tiles = new Tile[d * d];
        String line = "";
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
            switch(tType){
                case BASIC: t = new BasicTile(AllowedMovementType.values()[move]); break;
                case FLING: t = new FlingTile(AllowedMovementType.values()[move]);break;
            }
            tiles[i] = t;
            i++;
        }

        Level level = new Level(d);
        level.setTiles(tiles);
        level.setStartEnd(start, end);
        return level;
    }

    public void playerMoved(int nX,int nY){
        tiles[(nX/Tile.tileSize) + (nY/Tile.tileSize) * d].steppedOn(this);
    }
    private void setStartEnd(Point start, Point end) {
        this.start = start;
        this.end = end;
        player = new Player(start.x*Tile.tileSize,start.y * Tile.tileSize);
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }


    public AllowedMovementType currentMove(){
        return tiles[(player.getX()/Tile.tileSize) + (player.getY()/Tile.tileSize) * d].getMoves();
    }
}
