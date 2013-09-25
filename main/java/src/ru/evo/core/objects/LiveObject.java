package ru.evo.core.objects;

import ru.evo.common.Voc;

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

    private boolean hasTheGoal;

    private int satiety; // Сытость

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

    public boolean hasTheGoal() {
        return hasTheGoal;
    }

    public void setHasTheGoal(boolean hasTheGoal) {
        this.hasTheGoal = hasTheGoal;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public void decSatiety() {
        this.satiety--;
    }

    public abstract void move();

    public abstract void findGoal(Voc.evoObjects goalType);
}
