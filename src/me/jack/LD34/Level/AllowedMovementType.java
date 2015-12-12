package me.jack.LD34.Level;

/**
 * Created by Jack on 12/12/2015.
 */
public enum AllowedMovementType {
    UP_LEFT(0, 0, 0), UP_RIGHT(0, 1, 1), DOWN_LEFT(1, 0, 2), DOWN_RIGHT(1, 1, 3);

    private int UPDOWN, LEFTRIGHT, id;

    AllowedMovementType(int updown, int leftright, int id) {
        this.UPDOWN = updown;
        this.LEFTRIGHT = leftright;
        this.id = id;
    }

    public int getUPDOWN() {
        return UPDOWN;
    }

    public int getLEFTRIGHT() {
        return LEFTRIGHT;
    }

    public int getID() {
        return id;
    }
}
