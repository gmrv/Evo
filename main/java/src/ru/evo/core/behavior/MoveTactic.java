package ru.evo.core.behavior;

import ru.evo.core.infrastructure.CoordProxy;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 25.09.13
 * Time: 12:27
 * To change this template use File | Settings | File Templates.
 */
public abstract class MoveTactic {
    public abstract CoordProxy getNextStep(int currX, int currY, int targetX, int targetY);
}
