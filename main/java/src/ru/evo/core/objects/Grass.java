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

    private static final Color COLOR_FRESH = new Color(0, 255, 61);
    private static final Color COLOR_RIPE = new Color(2, 160, 5);
    private static final Color COLOR_DRY = new Color(205, 153, 4);
    public static final long PULSE_PEAK_FOR_GRASS = 500;

    public Grass() {
        this(0, 0);
    }

    public Grass(int aX, int aY) {
        this(aX, aY, 1);
    }

    public Grass(int aX, int aY, long aPulse) {
        setCoordX(aX);
        setCoordY(aY);
        setPulse(aPulse);
    }

    @Override
    public void wakeUp() {
        super.wakeUp();
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
        long pulse = getPulse();
        long health = getHealth();

        if(pulse<PULSE_PEAK_FOR_GRASS){
            setHealth(pulse);
        }else{
            setHealth(--health);
        }
    }

    public void setHealth(long aHealthPoint) {

        super.setHealth(aHealthPoint);

        long pulse = getPulse();
        long health = getHealth();

        if(pulse<PULSE_PEAK_FOR_GRASS){
            if(health<(PULSE_PEAK_FOR_GRASS/2)){
                setColor(COLOR_FRESH);
            }else{
                setColor(COLOR_RIPE);
            }
        }else{
            setColor(COLOR_DRY);
        }
    }
}
