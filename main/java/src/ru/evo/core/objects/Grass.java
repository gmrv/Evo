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

    private static final Color COLOR_FRESH = new Color(0, 255, 0);
    private static final Color COLOR_FADED = new Color(175, 217, 0);
    private static final Color COLOR_DRY = new Color(0, 57, 1);

    private float freshness = (float) 100.0;

    public Grass() {
        this(0,0);
    }

    public Grass(int aX, int aY) {
        setCoordX(aX);
        setCoordY(aY);
        setFreshness(100);
    }

    public Grass(int aX, int aY, float aFreshness) {
        setCoordX(aX);
        setCoordY(aY);
        setFreshness(aFreshness);
    }

    @Override
    public void wakeUp() {
        behavior();
        paint();
    }

    @Override
    public void paint() {
        Voc.g.setColor(getColor());
        Voc.g.fillOval(getCoordX()-1, getCoordY()-1, 5, 5);
    }

    @Override
    public void behavior() {
        setFreshness((float) (getFreshness()-0.1));
    }

    public float getFreshness() {
        return freshness;
    }

    public void setFreshness(float aFreshness) {
        this.freshness = aFreshness;
        if (freshness > 75){
            setColor(COLOR_FRESH);
        }else if (freshness < 75 && freshness > 30) {
            setColor(COLOR_FADED);
        } else if (freshness < 30 && freshness > 0) {
            setColor(COLOR_DRY);
        } else if (freshness < 0) {
            setDie(true);
        }
    }
}
