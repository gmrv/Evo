package ru.evo.core.objects;

import ru.evo.common.Voc;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 18.07.13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class Grass extends Landscape {

    private static final Color fresh = new Color(0, 255, 3);
    private static final Color faded = new Color(0, 127, 14);
    private static final Color dry = new Color(255,216, 0);

    private float freshness = (float) 100.0;
    private Color color = fresh;

    public Grass() {
    }

    public Grass(int aX, int aY) {
        setCoordX(aX);
        setCoordY(aY);
    }

    @Override
    public void wakeUp() {
        behavior();
        paint();
    }

    @Override
    public void paint() {
        if(freshness>0){
            Voc.g.setColor(color);
            Voc.g.drawOval(getCoordX(), getCoordY(), 3, 3);
        }else{

        }
    }

    @Override
    public void behavior() {
        freshness = (float) (freshness - 0.5);
        if(freshness<75 && freshness>30){
            color = faded;
        }else if(freshness<30 && freshness>0){
            color = dry;
        }else if(freshness<0){
            //Voc.mainContainer.remove(this);
        }
    }

    public float getFreshness() {
        return freshness;
    }

    public void setFreshness(float freshness) {
        this.freshness = freshness;
    }
}
