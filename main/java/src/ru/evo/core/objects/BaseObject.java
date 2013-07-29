package ru.evo.core.objects;
/**
 * Базовый класс для всех живых и неживых объектов
 *
 *
 */
import ru.evo.common.Voc;

import java.awt.*;

public abstract class BaseObject {

    private Graphics word;

    private String id;

    private int coordX;
    private int coordY;
    private Color color;

    // Количество тиков, которые поймал объект.
    // Служит для определения продолжительности жизни объекта.
    private long pulse;
    private long health;
    private boolean isDie;

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public long getPulse() {
        return pulse;
    }

    public void setPulse(long pulse) {
        this.pulse = pulse;
    }

    public void incPulse(){
        pulse++;
    }
    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
        if(health<=0){
            setDie(true);
        }
    }

    public void decHealth(long healthPoint) {
        this.health = this.health - healthPoint;
        if(health<=0){
            setDie(true);
        }
    }

    public void setWord(Graphics word) {
        this.word = word;
    }

    public Graphics getWord() {
        return word;
    }

    public boolean isDie() {
        return isDie;
    }

    public void setDie(boolean die) {
        if(die!=isDie){
            if(die){
                //Voc.writeLog(this.toString() + " was died. :(");
            }else{
                //Voc.writeLog(this.toString() + " was borned! :)");
            }
            isDie = die;
        }
    }

    /**
     * Метод вызываемый таймером для всех объектов
     * в нем должен быть размещен вызов блока логики поведения объекта
     */
    public void wakeUp(){
        incPulse();
        behavior();
        paint();
    }

    /**
     * Отрисовка самого себя на канвас
     */
    public abstract void paint();

    /**
     * Реализует поведение объекта
     */
    public abstract void behavior();

    public String toString(){
        StringBuilder result = new StringBuilder();
        result
            .append("id: ").append(getId()).append(" ")
            .append("{x=").append(getCoordX()).append(", ")
            .append("y=").append(getCoordY()).append("} ")
            .append(this.getClass().getSimpleName());
        return result.toString();
    }
}
