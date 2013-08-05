package ru.evo.core.behavior;

import ru.evo.common.Voc;
import ru.evo.core.infrastructure.CoordProxy;
import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.LiveObject;

import java.util.ArrayList;

public class Finder {

    LiveObject host;

    ArrayList<BaseObject> lastResult;

    public Finder(LiveObject aHost){
        host = aHost;
        lastResult = new ArrayList<BaseObject>();
    }

    public ArrayList<BaseObject> getObjectsInRange(int findRange, Voc.evoObjects aEvoObjects){
        ArrayList<BaseObject> result = new ArrayList<BaseObject>();

        int x1 = host.getCoordX() - findRange;
        int y1 = host.getCoordY() - findRange;
        int x2 = host.getCoordX() + findRange;
        int y2 = host.getCoordY() + findRange;

        for(BaseObject bo : Voc.mainContainer){
            String className = bo.getClass().getSimpleName().toUpperCase();
            if(Voc.evoObjects.valueOf(className) == aEvoObjects){
                int x = bo.getCoordX();
                int y = bo.getCoordY();
                //Voc.writeLog("{x = " + x + ", y = " + y + "}");
                if( x > x1 ){
                    //Voc.writeLog("x > x1");
                    if(x < x2){
                        //Voc.writeLog("x < x2");
                        if(y > y1){
                            //Voc.writeLog("y < y1");
                            if(y < y2){
                                //Voc.writeLog("y < y2");
                                result.add(bo);
                                //Voc.writeLog("ok. added. size = " + findedObjects.size());
                            }
                        }
                    }
                }
            }
        }

        return result;

    }

    public CoordProxy findGoalCoord(Voc.evoObjects aGoalType){
        CoordProxy result = new CoordProxy();

        lastResult = getObjectsInRange(Voc.HERBIVORE_FIND_RANGE, aGoalType);

        if(lastResult.size()>0){
            int index = Voc.getRand().nextInt(lastResult.size());
            result.x = lastResult.get(index).getCoordX();
            result.y = lastResult.get(index).getCoordY();
        }

        return result;
    }

    public BaseObject findGoalObject(Voc.evoObjects aGoalType){
        BaseObject result = null;

        lastResult = getObjectsInRange(Voc.HERBIVORE_FIND_RANGE, aGoalType);

        if(lastResult.size()>0){
            int index = Voc.getRand().nextInt(lastResult.size());
            result = lastResult.get(index);
        }

        return result;
    }

}
