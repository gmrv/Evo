package ru.evo.core.objects;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 14.07.13
 * Time: 0:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class LiveObject extends BaseObject {

    private int coordNextX;  // Next creature x coordinate
    private int coordNextY;  // Next creature y coordinate

    protected LiveObject() {
    }

    protected LiveObject(int aX, int aY) {
        super(aX, aY);
    }

    public int getCoordNextX() {
        return coordNextX;
    }

    public void setCoordNextX(int coordNextX) {
        this.coordNextX = coordNextX;
    }

    public int getCoordNextY() {
        return coordNextY;
    }

    public void setCoordNextY(int coordNextY) {
        this.coordNextY = coordNextY;
    }

    public abstract void move();
}
