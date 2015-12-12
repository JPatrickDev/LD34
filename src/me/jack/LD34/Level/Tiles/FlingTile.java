package me.jack.LD34.Level.Tiles;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import me.jack.LD34.Level.TileType;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 12/12/2015.
 */
public class FlingTile extends Tile {
    private int direction;

    public FlingTile(AllowedMovementType movementType, int direction) {
        super(TileType.FLING, movementType);
        this.direction = direction;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(fTiles[direction], x * Tile.tileSize, y * Tile.tileSize);
    }

    @Override
    public void steppedOn(Level level) {
        System.out.println("FDir: " + direction);
        level.player.fling(direction);
    }
}
