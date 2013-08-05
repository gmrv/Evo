package ru.evo.core.objects;

import ru.evo.common.Voc;
import ru.evo.core.behavior.Finder;
import ru.evo.core.infrastructure.CoordProxy;

import java.awt.*;
import java.util.ArrayList;

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
    }


    @Override
    public void paint() {
        if(getHealth()<1000){
            Voc.g.setColor(Color.YELLOW);
        }else{
            Voc.g.setColor(Color.RED);
        }

        Voc.g.drawOval(getCoordX(), getCoordY(), 1, 1);
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
                // да, делаю шаг к цели
                setNextSteepCoordTowardsGoal();
                move();
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
        setCoordX(getCoordNextX());
        setCoordY(getCoordNextY());
    }

    @Override
    public void findGoal(Voc.evoObjects goalType){
        BaseObject goal = finder.findGoalObject(goalType);
        if(goal!=null){
            setGoal(goal);
        }else{
            setCoordGoalX(Voc.getRand().nextInt(800));
            setCoordGoalY(Voc.getRand().nextInt(800));
        }
    }

    //todo: переместить в ru.evo.behavior.Mover
    //На вход будет получать координаты цели,
    //возвращать координаты следующего шага.
    void setNextSteepCoordTowardsGoal() {
        int i, L, xstart, ystart, xend, yend;
        float dX, dY;
        xstart = getCoordX();
        ystart = getCoordY();
        xend = getCoordGoalX();
        yend = getCoordGoalY();

        L = Math.max(Math.abs(xend - xstart), Math.abs(yend - ystart));

        if(L==0){
            setHasTheGoal(false);

            //todo: Временно проставляем сытость тут
            if(findIfood()){
                setSatiety(100);
                setHealth(maxHealth);
            }


            dX = 0;
            dY = 0;
        }else{
            dX = (getCoordGoalX() - getCoordX()) / L;
            dY = (getCoordGoalY() - getCoordY()) / L;
        }

        setCoordNextX(Math.round(getCoordX() + dX));
        setCoordNextY(Math.round(getCoordY() + dY));
    }

    private void findWater(){
        setCoordGoalX(Voc.getRand().nextInt(800));
        setCoordGoalY(Voc.getRand().nextInt(800));
    }

    private void findFood(){

        Grass food = (Grass) finder.findGoalObject(Voc.evoObjects.GRASS);

        if(food!=null){
            setGoal(food);
        }else{
            setCoordGoalX(Voc.getRand().nextInt(800));
            setCoordGoalY(Voc.getRand().nextInt(800));
        }
    }

    private void findSex(){
        setCoordGoalX(Voc.getRand().nextInt(800));
        setCoordGoalY(Voc.getRand().nextInt(800));
    }

    private boolean findIfood(){
        if(getGoal()!=null){
            if(getGoal().getCoord().equals(this.getCoord())){
                return true;
            }
        }
        return false;
    }


}

