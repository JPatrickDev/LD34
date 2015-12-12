package me.jack.LD34.Level;

import me.jack.LD34.States.InGameState;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

/**
 * Created by Jack on 12/12/2015.
 */
public abstract class Tile {

    private TileType type;
    private AllowedMovementType movementType;

    public static final int tileSize = 32;

    public static Image[] tiles = new Image[4];

    public static void init(){
        tiles[0] = ImageUtil.loadImage("res/upleft.png");
        tiles[1] = ImageUtil.loadImage("res/upright.png");
        tiles[2] = ImageUtil.loadImage("res/downleft.png");
        tiles[3] = ImageUtil.loadImage("res/downright.png");
    }

    public Tile(TileType type, AllowedMovementType movementType) {
        this.type = type;
        this.movementType = movementType;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(tiles[movementType.getID()],x * Tile.tileSize,y*Tile.tileSize);
    }

    public AllowedMovementType getMoves() {
        return movementType;
    }

    public abstract void steppedOn(Level level);
}
