package ru.evo.core;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 14.07.13
 * Time: 0:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class LiveObject {
    protected LiveObject() {
    }

    public LiveObject(int aX, int aY) {
        setCoordX(aX);
        setCoordY(aY);
    }

    public abstract void paint(int aX, int aY);

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

    public int getCoordNextX() {
        return coordNextX;
    }

    public void setCoordNextX(int coordNextX) {
        this.coordNextX = coordNextX;
    }

    public int getCoordNextY() {
        return coordNextY;
    }

    public void setCoordNextY(int coordNextY) {
        this.coordNextY = coordNextY;
    }

    private int coordX;  // Current creature x coordinate
    private int coordY;  // Current creature y coordinate
    private int coordNextX;  // Next creature x coordinate
    private int coordNextY;  // Next creature y coordinate

    private Graphics word;

    public void setWord(Graphics word) {
        this.word = word;
    }

    public Graphics getWord() {
        return word;
    }

    public abstract void wakeUp();
}
