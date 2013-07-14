package ru.evo.core.infrastructure;

import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.Herbivore;

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

}
