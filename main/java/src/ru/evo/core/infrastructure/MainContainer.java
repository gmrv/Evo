package ru.evo.core.infrastructure;

import ru.evo.common.Voc;
import ru.evo.core.objects.BaseObject;
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

    public MainContainer() {
    }

    public void add(BaseObject aEvoObject){
        mainContainer.add(aEvoObject);
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
        for(BaseObject bo : mainContainer){
                bo.wakeUp();
        }
    }

    public void sendPaint(){
        for(BaseObject bo : mainContainer){
            bo.paint();
        }
    }

    public void dropDieObject() {
        for(BaseObject bo : mainContainer){
            if(bo.isDie()){
                mainContainer.remove(bo);
            }
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator<BaseObject>() {
            private int index = 0;

            public boolean hasNext() {
                return index < mainContainer.size();
            }

            public BaseObject next() {
                return mainContainer.get(index++);
            }

            public void remove() {
                mainContainer.remove(index);
            }
        };
    }
}
