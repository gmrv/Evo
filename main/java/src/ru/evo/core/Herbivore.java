package ru.evo.core;

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

    Random rand = new Random(47);

    public Herbivore(int aX, int aY) {
       super(aX, aY);
    }

    @Override
    public void paint(int aX, int aY) {
        getWord().setColor(Color.RED);
        getWord().drawOval(aX, aY, 1, 1);
    }

    @Override
    public void wakeUp() {


        int horisontal = rand.nextInt(10);
        int vertical = rand.nextInt(10);

        System.out.println(" horisontal = " + horisontal + " vertical = " + vertical);

        setCoordNextX(horisontal%2==0 ? getCoordX()-1 : getCoordX()+1);
        setCoordNextY(vertical%2==0 ? getCoordY()-1 : getCoordY()+1 );

        move();
    }

    private void move() {
        System.out.println(" x = " + getCoordNextX() + " y = " + getCoordNextY());
        setCoordX(getCoordNextX()); setCoordY(getCoordNextY());
        paint(getCoordNextX(), getCoordNextY());
    }
}
