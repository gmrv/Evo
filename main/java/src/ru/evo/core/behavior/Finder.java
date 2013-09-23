package ru.evo.core.behavior;

import ru.evo.common.Voc;
import ru.evo.core.infrastructure.CoordProxy;
import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.LiveObject;

import java.util.ArrayList;

public class Finder {
    //todo: Реализовать его как посредника, который будет оперировать с данными host.
    //Убрать все данные из этого класса. Тут только логика.

    LiveObject host;

    ArrayList<BaseObject> lastResult;

    public Finder(LiveObject aHost) {
        host = aHost;
        lastResult = new ArrayList<BaseObject>();
    }

    public ArrayList<BaseObject> getObjectsInRange(int findRadius, Voc.evoObjects aEvoObjects) {
        ArrayList<BaseObject> result = new ArrayList<BaseObject>();

        int R = findRadius;
        int x0 = host.getCoordX();
        int y0 = host.getCoordY();
        int x1 = host.getCoordX() - findRadius;
        int y1 = host.getCoordY() - findRadius;
        int x2 = host.getCoordX() + findRadius;
        int y2 = host.getCoordY() + findRadius;


        for (BaseObject bo : Voc.mainContainer) {
            String className = bo.getClass().getSimpleName().toUpperCase();
            if (Voc.evoObjects.valueOf(className) == aEvoObjects) {
                int x = bo.getCoordX();
                int y = bo.getCoordY();
                //Voc.writeLog("{x = " + x + ", y = " + y + "}");

                if (((x - x0) * (x - x0) + (y - y0) * (y - y0)) <= (R * R)) {     //Crucial feature
                    result.add(bo);
                }

            }
        }

        return result;

    }

    public CoordProxy findGoalCoord(Voc.evoObjects aGoalType) {
        CoordProxy result = new CoordProxy();

        lastResult = getObjectsInRange(Voc.HERBIVORE_FIND_RADIUS, aGoalType);

        if (lastResult.size() > 0) {
            int index = Voc.getRand().nextInt(lastResult.size());
            result.x = lastResult.get(index).getCoordX();
            result.y = lastResult.get(index).getCoordY();
        }

        return result;
    }

    public BaseObject findGoalObject(Voc.evoObjects aGoalType) {
        BaseObject result = null;

        lastResult = getObjectsInRange(Voc.HERBIVORE_FIND_RADIUS, aGoalType);

        if (lastResult.size() > 0) {
            int index = Voc.getRand().nextInt(lastResult.size());
            result = lastResult.get(index);
        }

        return result;
    }

}
