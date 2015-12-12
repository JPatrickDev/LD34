package me.jack.LD34.Level.Tiles;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import me.jack.LD34.Level.TileType;

/**
 * Created by Jack on 12/12/2015.
 */
public class FlingTile extends Tile {
    public FlingTile(AllowedMovementType movementType) {
        super(TileType.FLING, movementType);
    }


    @Override
    public void steppedOn(Level level) {

    }
}
