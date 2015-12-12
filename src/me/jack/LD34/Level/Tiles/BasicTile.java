package me.jack.LD34.Level.Tiles;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import me.jack.LD34.Level.TileType;

/**
 * Created by Jack on 12/12/2015.
 */
public class BasicTile extends Tile {

    public BasicTile(AllowedMovementType movementType) {
        super(TileType.BASIC, movementType);
    }

    @Override
    public void steppedOn(Level level) {
        System.out.println("Stepped on");
    }
}
