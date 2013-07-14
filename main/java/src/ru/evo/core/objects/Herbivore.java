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
        getWord().setColor(Color.RED);
        getWord().drawOval(getCoordX(), getCoordY(), 1, 1);
    }

    @Override
    public void wakeUp() {
        int horisontal = Voc.getRand().nextInt(10);
        int vertical = Voc.getRand().nextInt(10);

        setCoordNextX(horisontal%2==0 ? getCoordX()-1 : getCoordX()+1);
        setCoordNextY(vertical%2==0 ? getCoordY()-1 : getCoordY()+1 );

        move();
    }

    @Override
    public void move() {
        setCoordX(getCoordNextX());
        setCoordY(getCoordNextY());
        paint();
    }
}
