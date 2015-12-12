package me.jack.LD34.Level.Tiles;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import me.jack.LD34.Level.TileType;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 12/12/2015.
 */
public class WallTile extends Tile{

    public WallTile() {
        super(TileType.WALL,AllowedMovementType.NONE);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(wallTile,x*Tile.tileSize,y*Tile.tileSize);
    }

    @Override
    public void steppedOn(Level level) {
        System.out.println("That's not supposed to happen");
    }
}
