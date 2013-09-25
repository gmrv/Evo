package ru.evo.core.behavior;

import ru.evo.core.infrastructure.CoordProxy;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 25.09.13
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
 */
public class DirectMoveTactic extends MoveTactic {
    @Override
    public CoordProxy getNextStep(int currX, int currY, int targetX, int targetY) {
        double i, L;
        double dX, dY;

        L = Math.sqrt((targetX - currX)*(targetX - currX)+ (targetY - currY)*(targetY - currY));

        if (L == 0) {
            dX = 0;
            dY = 0;
        } else {
            dX = (targetX - currX) / L;
            dY = (targetY - currY) / L;
        }
        int idX =(int)Math.round(dX);
        int idY = (int) Math.round(dY);

        CoordProxy nextStep = new CoordProxy(currX + idX, currY + idY);
        return nextStep;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
