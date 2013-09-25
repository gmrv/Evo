package ru.evo.core.objects;

import ru.evo.common.Voc;
import ru.evo.core.behavior.DirectMoveTactic;
import ru.evo.core.behavior.Finder;
import ru.evo.core.behavior.Mover;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 14.07.13
 * Time: 1:22
 * To change this template use File | Settings | File Templates.
 */
public class Herbivore extends LiveObject {

    public long maxHealth = 3000;

    private Finder finder;
    private Mover mover;

    public Herbivore(int aX, int aY) {
        this(aX, aY, 3000);
    }

    public Herbivore(int aX, int aY, long aHealth) {
        this(aX, aY, aHealth, 100);
    }

    public Herbivore(int aX, int aY, long aHealth, int aSatiety) {
        super(aX, aY);
        setHealth(aHealth);
        setSatiety(aSatiety);
        finder = new Finder(this);
        mover = new Mover(this);
        mover.setMoveTactic(new DirectMoveTactic());
//        mover.setMoveTactic(new StairsMoveTactic());
    }

    @Override
    public void paint() {
        if(getHealth()<1000){
            Voc.g.setColor(Color.YELLOW);
        }else{
            Voc.g.setColor(Color.RED);
        }
        Voc.g.drawOval(getCoordX(), getCoordY(), 1, 1);
        Voc.g.setColor(Color.DARK_GRAY);
        Voc.g.drawOval(getCoordX()-Voc.HERBIVORE_FIND_RADIUS, getCoordY()-Voc.HERBIVORE_FIND_RADIUS, Voc.HERBIVORE_FIND_RADIUS *2, Voc.HERBIVORE_FIND_RADIUS *2);
        //Voc.g.drawImage(Voc.img, getCoordX(), getCoordY(), 3, 3, null);
    }

    @Override
    public void behavior() {

        decSatiety();

        int lSatiety = getSatiety();

        // я голоден
        if (lSatiety < 100 && lSatiety > 75 ){
            // я хочу размножаться
        }else if(lSatiety < 75){
            // я хочу есть
            // я на пути к еде?
            if(hasTheGoal()){
                // я достиг цели?
                if(mover.getGoal().getCoord().equals(this.getCoord())){
                    setSatiety(100);
                    setHealth(maxHealth);
                    setHasTheGoal(false);
                }else{
                    // нет, делаю шаг к цели
                    mover.setNextSteepCoordTowardsGoal();
                    move();
                }
            }else{
                // нет, ищу еду
                findGoal(Voc.evoObjects.GRASS);
            }
        }

        if(lSatiety < 10){
            //подыхаем потихоньку
            decHealth(2);
        }
    }

    @Override
    public void move() {
        mover.move();
    }

    @Override
    public void findGoal(Voc.evoObjects goalType){
        BaseObject goal = finder.findGoalObject(goalType);
        if(goal!=null){
            mover.setGoal(goal);
        }else{
            boolean vertInc = Voc.getRand().nextBoolean();
            boolean horiInc = Voc.getRand().nextBoolean();

            if(horiInc){
                this.setCoordNextX(getCoordX()+1);
            }else{
                this.setCoordNextX(getCoordX()-1);
            }

            if(vertInc){
                this.setCoordNextY(getCoordY()+1);
            }else{
                this.setCoordNextY(getCoordY()-1);
            }

            move();
        }

    }
}

