package ru.evo.core.objects;
/**
 * Базовый класс для всех живых и неживых объектов
 *
 *
 */
import ru.evo.common.Voc;

import java.awt.Graphics;

public abstract class BaseObject {

    private String id;
    private int coordX;
    private int coordY;
    private Graphics word;

    protected BaseObject() {
        setId(Voc.getNewId());
    }

    protected BaseObject(int aX, int aY) {
        setId(Voc.getNewId());
        setCoordX(aX);
        setCoordY(aY);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void setWord(Graphics word) {
        this.word = word;
    }

    public Graphics getWord() {
        return word;
    }

    /**
     * Метод вызываемый таймером для всех объектов
     * в нем должен быть размещен вызов блока логики поведения объекта
     */
    public abstract void wakeUp();

    public abstract void paint();

    public String toString(){
        StringBuilder result = new StringBuilder();
        result
            .append("id: ").append(getId()).append(" ")
            .append("{x=").append(getCoordX()).append(", ")
            .append("y=").append(getCoordY()).append("}");
        return result.toString();
    }
}
