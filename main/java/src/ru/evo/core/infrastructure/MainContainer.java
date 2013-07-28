package ru.evo.core.infrastructure;

import ru.evo.common.Voc;
import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.Grass;
import ru.evo.core.objects.Herbivore;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Основной класс контейнер.
 * Обеспечивает хранение и доступ ко всем объектом из основного таймера.
 * todo: Нужно переделать в синглтон
 */
public class MainContainer implements Iterable<BaseObject> {

    private ArrayList<BaseObject> mainContainer = new ArrayList<BaseObject>();
    private ArrayList<BaseObject> toDelete = new ArrayList<BaseObject>();

    public MainContainer() {
    }

    public void add(BaseObject aEvoObject){
        getMainContainer().add(aEvoObject);
        //Voc.writeLog(aEvoObject + " added");
    }

    public void remove(BaseObject aEvoObject){
        mainContainer.remove(aEvoObject);
    }

    public BaseObject get(int aIndex){
        return mainContainer.get(aIndex);
    }

    /**
     * Метод для посылки сообщения всем объектам в контейнере
     */
    public void sendWakeUp(){
        for(BaseObject bo : getMainContainer()){
                bo.wakeUp();
        }
    }

    public void sendPaint(){
        for(BaseObject bo : getMainContainer()){
            bo.paint();
        }
    }

    public void dropDieObject() {

        int dieGrassCounter = 0;

        for(BaseObject bo : getMainContainer()){
            if(bo.isDie()){
                toDelete.add(bo);
                if(true){
                  dieGrassCounter = dieGrassCounter+1;
                }
            }
        }

        getMainContainer().removeAll(toDelete);

        for(int i=0; i < dieGrassCounter; i++){
            this.add(
                    new Grass(
                            Voc.getRand().nextInt(800),
                            Voc.getRand().nextInt(800),
                            1
                    )
            );
        }

        toDelete.clear();
    }

    @Override
    public Iterator iterator() {
        return new Iterator<BaseObject>() {
            private int index = 0;

            public boolean hasNext() {
                return index < getMainContainer().size();
            }

            public BaseObject next() {
                return getMainContainer().get(index++);
            }

            public void remove() {
                getMainContainer().remove(index);
            }
        };
    }

    public synchronized ArrayList<BaseObject> getMainContainer() {
        return mainContainer;
    }

    public void setMainContainer(ArrayList<BaseObject> mainContainer) {
        this.mainContainer = mainContainer;
    }
}
