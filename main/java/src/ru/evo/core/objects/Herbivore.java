package ru.evo.core.objects;

import ru.evo.common.Voc;

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
                findGoal(Voc.GOAL_TYPE_FOOD);
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
    public void findGoal(int goalType){
        switch (goalType) {
            case 0:
                findWater();
                break;
            case 1:
                findFood();
                break;
            case 2:
                findSex();
                break;
            default:
                Voc.writeLog("Invalid Goal Type:" + goalType); //throw new InvalidGoalTypeException(Integer.toString(goalType));
        }
    }

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

        //todo: Вынести кусок который возвращает массив найденных объектов в одтельный метод getNearestGrasses(findRadius)

        //Voc.writeLog( this + " search started");

        int findRadius = 50;

        int x1 = getCoordX() - findRadius;
        int y1 = getCoordY() - findRadius;
        int x2 = getCoordX() + findRadius;
        int y2 = getCoordY() + findRadius;

        //Voc.writeLog("{xa = " + getCoordX() + ", ya = " + getCoordY() + "}");
        //Voc.writeLog("{x1 = " + x1 + ", y1 = " + y1 + "}, " + "{x2 = " + x2 + ", y2 = " + y2 + "}");

        ArrayList<BaseObject> findedObjects = new ArrayList<BaseObject>();

        for(BaseObject bo : Voc.mainContainer){
            if(bo.getClass().getSimpleName().equals("Grass")){
                int x = bo.getCoordX();
                int y = bo.getCoordY();
                //Voc.writeLog("{x = " + x + ", y = " + y + "}");
                if( x > x1 ){
                    //Voc.writeLog("x > x1");
                    if(x < x2){
                        //Voc.writeLog("x < x2");
                        if(y > y1){
                            //Voc.writeLog("y < y1");
                            if(y < y2){
                                //Voc.writeLog("y < y2");
                                findedObjects.add(bo);
                                //Voc.writeLog("ok. added. size = " + findedObjects.size());
                            }
                        }
                    }
                }
            }
        }

        //todo: Поиск ближайшего объекта
        for(BaseObject bo : findedObjects){

        }

        if(findedObjects.size()>0){
            int index = Voc.getRand().nextInt(findedObjects.size());
            setCoordGoalX(findedObjects.get(index).getCoordX());
            setCoordGoalY(findedObjects.get(index).getCoordY());
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

        //todo: Использовать getNearestGrasses(findRadius)

        int findRadius = 5;

        int x1 = getCoordX() - findRadius;
        int y1 = getCoordY() - findRadius;
        int x2 = getCoordX() + findRadius;
        int y2 = getCoordY() + findRadius;

        ArrayList<BaseObject> findedObjects = new ArrayList<BaseObject>();

        for(BaseObject bo : Voc.mainContainer){
            if(bo.getClass().getSimpleName().equals("Grass")){
                int x = bo.getCoordX();
                int y = bo.getCoordY();
                //Voc.writeLog("{x = " + x + ", y = " + y + "}");
                if( x > x1 ){
                    //Voc.writeLog("x > x1");
                    if(x < x2){
                        //Voc.writeLog("x < x2");
                        if(y > y1){
                            //Voc.writeLog("y < y1");
                            if(y < y2){
                                //Voc.writeLog("y < y2");
                                findedObjects.add(bo);
                                //Voc.writeLog("ok. added. size = " + findedObjects.size());
                            }
                        }
                    }
                }
            }
        }

        if (findedObjects.size() > 0){
            return true;
        }else{
            return false;
        }
    }
}

