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
    public static Image[] fTiles = new Image[4];

    public static Image tpTile, tpTarget, wallTile, moveableTile, border;

    public static void init() {
        Image basic = ImageUtil.loadImage("res/upleft.png");

        tiles[0] = basic;
        tiles[1] = rotate(basic, 90);
        tiles[2] = rotate(basic, 270);
        tiles[3] = rotate(basic, 180);

        Image flingBasic = ImageUtil.loadImage("res/flingleft.png");
        fTiles[0] = flingBasic;
        fTiles[1] = rotate(flingBasic,90);
        fTiles[2] = rotate(flingBasic,270);
        fTiles[3] = rotate(flingBasic,180);

        tpTile = ImageUtil.loadImage("res/tptile.png");
        wallTile = ImageUtil.loadImage("res/walltile.png");
        moveableTile = ImageUtil.loadImage("res/moveabletile.png");
        border = ImageUtil.loadImage("res/border.png");
    }

    public static Image rotate(Image i, int deg) {
        Image ii = i.copy();
        ii.rotate(deg);
        return ii;
    }

    public Tile(TileType type, AllowedMovementType movementType) {
        this.type = type;
        this.movementType = movementType;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(tiles[movementType.getID()], x * Tile.tileSize, y * Tile.tileSize);
    }

    public AllowedMovementType getMoves() {
        return movementType;
    }

    public abstract void steppedOn(Level level);

    public TileType getType() {
        return type;
    }
}
