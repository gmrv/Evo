package ru.evo.core.objects;

import ru.evo.common.Voc;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 14.07.13
 * Time: 1:22
 * To change this template use File | Settings | File Templates.
 */
public class Herbivore extends LiveObject {

    public Herbivore(int aX, int aY) {
        super(aX, aY);
    }

    @Override
    public void paint() {
        Voc.g.setColor(Color.RED);
        Voc.g.drawOval(getCoordX(), getCoordY(), 1, 1);
    }

    @Override
    public void behavior() {
        if(isHasTheGoal()){
            setNextSteepCoordForGoal();
            move();
        }else{
            setCoordGoalX(Voc.getRand().nextInt(800));
            setCoordGoalY(Voc.getRand().nextInt(800));
        }
    }

    @Override
    public void wakeUp() {
        behavior();
        paint();
    }

    @Override
    public void move() {
        setCoordX(getCoordNextX());
        setCoordY(getCoordNextY());
    }

    void setNextSteepCoordForGoal() {
        int i, L, xstart, ystart, xend, yend;
        float dX, dY;
        xstart = getCoordX();
        ystart = getCoordY();
        xend = getCoordGoalX();
        yend = getCoordGoalY();

        L = Math.max(Math.abs(xend - xstart), Math.abs(yend - ystart));

        if(L==0){
            setHasTheGoal(false);
            dX = 0;
            dY = 0;
        }else{
            dX = (getCoordGoalX() - getCoordX()) / L;
            dY = (getCoordGoalY() - getCoordY()) / L;
        }

        setCoordNextX(Math.round(getCoordX() + dX));
        setCoordNextY(Math.round(getCoordY() + dY));
    }
}
