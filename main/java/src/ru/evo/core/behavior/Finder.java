package ru.evo.core.behavior;

import ru.evo.common.Voc;
import ru.evo.core.infrastructure.CoordProxy;
import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.LiveObject;

import java.util.ArrayList;

// Класс для поиска объектов игры. Включается композицией в объекты обладающие способностью искать.
public class Finder {
    //todo: Реализовать его как посредника, который будет оперировать с данными host.
    //Убрать все данные из этого класса. Тут только логика.

    //Объект обладающий способностью искать.
    LiveObject host;

    //Массив с результатами последнего поиска.
    ArrayList<BaseObject> lastResult;

    public Finder(LiveObject aHost) {
        host = aHost;
        lastResult = new ArrayList<BaseObject>();
    }

    //Возвращает все объекты заданного класа в заданном радиусе от объекта host (текущего объекта)
    public ArrayList<BaseObject> getObjectsInRange(int findRadius, Voc.evoObjects aEvoObjects) {
        ArrayList<BaseObject> result = new ArrayList<BaseObject>();

        int x0 = host.getCoordX();
        int y0 = host.getCoordY();

        for (BaseObject bo : Voc.mainContainer) {
            String className = bo.getClass().getSimpleName().toUpperCase();
            if (Voc.evoObjects.valueOf(className) == aEvoObjects) {
                int x = bo.getCoordX();
                int y = bo.getCoordY();
                //Voc.writeLog("{x = " + x + ", y = " + y + "}");
                if (((x - x0) * (x - x0) + (y - y0) * (y - y0)) <= (findRadius * findRadius)) {
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

    //Возвращает объект-цель. На вход получает тип искомой цели.
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
