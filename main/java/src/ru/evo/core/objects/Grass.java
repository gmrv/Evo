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

    //private static final Color fresh = new Color(0, 255, 3);
    //private static final Color faded = new Color(0, 127, 14);
    //private static final Color dry = new Color(255, 216, 0);

    private static final Color fresh = new Color(0, 0, 0);
    private static final Color faded = new Color(127, 127, 127);
    private static final Color dry = new Color(255, 255, 255);

    private float freshness = (float) 100.0;
    private Color color = fresh;

    public Grass() {
    }

    public Grass(int aX, int aY) {
        setCoordX(aX);
        setCoordY(aY);
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
        Voc.g.setColor(color);
        Voc.g.drawOval(getCoordX(), getCoordY(), 3, 3);
    }

    @Override
    public void behavior() {
        setFreshness((float) (getFreshness()-0.5));
    }

    public float getFreshness() {
        return freshness;
    }

    public void setFreshness(float aFreshness) {
        this.freshness = aFreshness;
        if (freshness < 75 && freshness > 30) {
            color = faded;
        } else if (freshness < 30 && freshness > 0) {
            color = dry;
        } else if (freshness < 0) {
            setDie(true);
        }
    }
}
