package ru.evo.core.objects;
/**
 * Базовый класс для всех живых и неживых объектов
 *
 *
 */
import java.awt.Graphics;

public abstract class BaseObject {

    private int coordX;
    private int coordY;
    private Graphics word;

    protected BaseObject() {
    }

    protected BaseObject(int aX, int aY) {
        setCoordX(aX);
        setCoordY(aY);
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
}
