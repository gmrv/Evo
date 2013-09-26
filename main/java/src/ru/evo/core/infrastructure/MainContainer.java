package ru.evo.core.infrastructure;

import ru.evo.common.Voc;
import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.Grass;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Основной класс контейнер.
 * Обеспечивает хранение и доступ ко всем объектом из основного таймера.
 * todo: Синглтон
 */
public class MainContainer implements Iterable<BaseObject> {

    //Активные объекты
    private ArrayList<BaseObject> mainContainer = new ArrayList<BaseObject>();

    //Мертвые объекты на удаление
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

    // Вызываем метод прорисовки себя у всех объектов
    public void sendPaint(){
        for(BaseObject bo : getMainContainer()){
            bo.paint();
        }
    }

    // Удаление мертвых объектов
    public void dropDieObject() {

        int grassCounter = 0;
        int herbivoreCounter = 0;

        for(BaseObject bo : getMainContainer()){
            String className = bo.getClass().getSimpleName().toUpperCase();
            if(bo.isDie()){
                toDelete.add(bo);
            }else{
                switch (Voc.evoObjects.valueOf(className)){
                    case GRASS: grassCounter++; break;
                    case HERBIVORE: herbivoreCounter++; break;
                }
            }
        }

        getMainContainer().removeAll(toDelete);

        if(grassCounter < 2){
            this.add(new Grass(100, 100, 1));
            this.add(new Grass(140, 140, 1));
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
