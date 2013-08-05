package ru.evo.core.objects;

import ru.evo.common.Voc;
import ru.evo.core.exceptions.InvalidGoalTypeException;
import ru.evo.core.infrastructure.CoordProxy;

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

    private int coordGoalX;
    private int coordGoalY;
    private CoordProxy coordGoal;
    private BaseObject goal;

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

    public int getCoordGoalX() {
        return coordGoalX;
    }

    public void setCoordGoalX(int coordGoalX) {
        this.coordGoalX = coordGoalX;
        this.setHasTheGoal(true);
    }

    public int getCoordGoalY() {
        return coordGoalY;
    }

    public void setCoordGoalY(int coordGoalY) {
        this.coordGoalY = coordGoalY;
        this.setHasTheGoal(true);
    }

    public CoordProxy getCoordGoal() {
        coordGoal.x = getCoordGoalX();
        coordGoal.y = getCoordGoalY();
        return coordGoal;
    }

    public void setCoordGoal(CoordProxy coordGoal) {
        this.coordGoal = coordGoal;
        setCoordGoalX(coordGoal.x);
        setCoordGoalY(coordGoal.y);
    }

    public BaseObject getGoal() {
        return goal;
    }

    public void setGoal(BaseObject goal) {
        this.goal = goal;
        setCoordGoal(goal.getCoord());
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
