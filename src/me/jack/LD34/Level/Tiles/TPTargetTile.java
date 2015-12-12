package me.jack.LD34.Level.Tiles;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import me.jack.LD34.Level.TileType;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 12/12/2015.
 */
public class TPTargetTile extends Tile {

    private Color color;

    public TPTargetTile(AllowedMovementType movementType, Color color) {
        super(TileType.TPTARGET, movementType);
        this.color = color;
    }


    @Override
    public void render(Graphics g, int x, int y) {
        super.render(g, x, y);
        g.setColor(color);
        g.drawRect(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize);
        g.setColor(Color.white);
    }

    @Override
    public void steppedOn(Level level) {

    }
}
