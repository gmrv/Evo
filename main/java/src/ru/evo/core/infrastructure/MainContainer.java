package ru.evo.core.infrastructure;

import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.Herbivore;

import java.awt.*;
import java.util.ArrayList;

/**
 * Основной класс контейнер.
 * Обеспечивает хранение и доступ ко всем объектом из основного таймера.
 * todo: Нужно переделать в синглтон
 */
public class MainContainer {

    private ArrayList<BaseObject> mainContainer = new ArrayList<BaseObject>();

    public MainContainer() {
    }

    public void add(BaseObject aEvoObject){
        mainContainer.add(aEvoObject);
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
            if(bo.isDie()){
                //Выкидывает java.util.ConcurrentModificationException
                //mainContainer.remove(bo);
            }else{
                bo.wakeUp();
            }

        }
    }

    public void sendPaint(){
        for(BaseObject bo : mainContainer){
            bo.paint();
        }
    }

}
