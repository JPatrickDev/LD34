package me.jack.LD34.Level;

import org.newdawn.slick.Graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Jack on 12/12/2015.
 */
public class Level {

    private int d;

    private Tile[] tiles;

    public Level(int d) {
        this.d = d;
        tiles = new Tile[d * d];
        for (int i = 0; i != d * d; i++) {
            tiles[i] = new Tile(TileType.BASIC, AllowedMovementType.DOWN_LEFT);
        }
    }


    public void render(Graphics g) {
        for (int x = 0; x != d; x++) {
            for (int y = 0; y != d; y++) {
                tiles[x + y * d].render(g, x, y);
            }
        }
    }

    public static Level load(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        int d = scanner.nextInt();

        int i = 0;
        Tile[] tiles = new Tile[d * d];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("#"))
                continue;
            if (line.equals(""))
                continue;
            String[] split = line.split(":");
            int type = Integer.parseInt(split[0]);
            int move = Integer.parseInt(split[1]);
            Tile t = new Tile(TileType.values()[type], AllowedMovementType.values()[move]);
            tiles[i] = t;
            i++;
        }

        Level level = new Level(d);
        level.setTiles(tiles);
        return level;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }
}
