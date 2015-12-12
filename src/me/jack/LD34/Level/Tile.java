package me.jack.LD34.Level;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 12/12/2015.
 */
public class Tile{

    private TileType type;
    private AllowedMovementType movementType;

    public static final int tileSize = 32;
    public Tile(TileType type,AllowedMovementType movementType){
        this.type = type;
        this.movementType = movementType;
    }

    public void render(Graphics g,int x,int y){
        if(type == TileType.BASIC)
        g.setColor(Color.red);
        else
        g.setColor(Color.blue);
        g.fillRect(x*tileSize,y*tileSize,tileSize,tileSize);
        g.setColor(Color.white);
        g.drawRect(x*tileSize,y*tileSize,tileSize,tileSize);
    }
}
