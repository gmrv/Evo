package ru.evo.core.behavior;

import ru.evo.core.infrastructure.CoordProxy;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 25.09.13
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public class StairsMoveTactic extends MoveTactic {
    @Override
    public CoordProxy getNextStep(int currX, int currY, int targetX, int targetY) {
        int i, L;
        float dX, dY;

        L = Math.max(Math.abs(targetX - currX), Math.abs(targetY - currY));

        if (L == 0) {
            dX = 0;
            dY = 0;
        } else {
            dX = (targetX - currX) / L;
            dY = (targetY - currY) / L;
        }
        int idX=(int)dX;
        int idY=(int)dY;

        CoordProxy nextStep=new CoordProxy(currX+idX , currY+idY);
        return nextStep;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
