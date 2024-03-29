<<<<<<< HEAD
package ru.evo.core.behavior;

import ru.evo.core.infrastructure.CoordProxy;
import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.LiveObject;

//Класс для вычислений координат передвижения. Включается композицией в объкты способные двигаться.
public class Mover {

    //todo: Реализовать его как посредника, который будет оперировать с данными host.
    //Убрать все данные из этого класса. Тут только логика.

    //Объект обладающий возможностью двигаться
    private LiveObject host;

    //Объект цель
    private BaseObject goal;

    //Коодринаты цели
    private int coordGoalX;
    private int coordGoalY;

    //Коодринаты цели в более удобной форме
    private CoordProxy coordGoal;

    private MoveTactic moveTactic;

    public Mover(LiveObject aHost){
        host = aHost;
    }

    public int getCoordGoalX() {
        return coordGoalX;
    }

    public void setCoordGoalX(int coordGoalX) {
        this.coordGoalX = coordGoalX;
        host.setHasTheGoal(true);
    }

    public int getCoordGoalY() {
        return coordGoalY;
    }

    public void setCoordGoalY(int coordGoalY) {
        this.coordGoalY = coordGoalY;
        host.setHasTheGoal(true);
    }

    public CoordProxy getCoordGoal() {
        coordGoal.x = getCoordGoalX();
        coordGoal.y = getCoordGoalY();
        return coordGoal;
    }

    public void setCoordGoal(CoordProxy coordGoal) {
        this.coordGoal = coordGoal;
        setCoordGoalX(coordGoal.x);
        setCoordGoalY(coordGoal.y);
    }

    public BaseObject getGoal() {
        return goal;
    }

    public void setGoal(BaseObject goal) {
        this.goal = goal;
        setCoordGoal(goal.getCoord());
    }

    public MoveTactic getMoveTactic() {
        return moveTactic;
    }

    public void setMoveTactic(MoveTactic moveTactic) {
        this.moveTactic = moveTactic;
    }

    //Вычисляем следующий шаг по направлению к цели
    public void setNextSteepCoordTowardsGoal() {
      /*  CoordProxy result = new CoordProxy();
        int i, L, xstart, ystart, xend, yend;
        float dX, dY;
        xstart = host.getCoordX();
        ystart = host.getCoordY();
        xend = getCoordGoalX();
        yend = getCoordGoalY();

        L = Math.max(Math.abs(xend - xstart), Math.abs(yend - ystart));

        if(L==0){
            dX = 0;
            dY = 0;
        }else{
            dX = (getCoordGoalX() - host.getCoordX()) / L;
            dY = (getCoordGoalY() - host.getCoordY()) / L;
        }
        host.setCoordNextX(Math.round(host.getCoordX() + dX));
        host.setCoordNextY(Math.round(host.getCoordY() + dY));*/
        CoordProxy result = moveTactic.getNextStep(host.getCoordX(),host.getCoordY(),getCoordGoalX(),getCoordGoalY()) ;
        host.setCoordNextX(result.x);
        host.setCoordNextY(result.y);
    }

    //Передвигаем объект хозяин
    public void move(){
        host.setCoordX(host.getCoordNextX());
        host.setCoordY(host.getCoordNextY());
    }


}
=======
package ru.evo.core.behavior;

import ru.evo.core.infrastructure.CoordProxy;
import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.LiveObject;

//Класс для вычислений координат передвижения. Включается композицией в объкты способные двигаться.
public class Mover {

    //todo: Реализовать его как посредника, который будет оперировать с данными host.
    //Убрать все данные из этого класса. Тут только логика.

    //Объект обладающий возможностью двигаться
    private LiveObject host;

    //Объект цель
    private BaseObject goal;

    //Коодринаты цели
    private int coordGoalX;
    private int coordGoalY;

    //Коодринаты цели в более удобной форме
    private CoordProxy coordGoal;

    private MoveTactic moveTactic;

    public Mover(LiveObject aHost){
        host = aHost;
    }

    public int getCoordGoalX() {
        return coordGoalX;
    }

    public void setCoordGoalX(int coordGoalX) {
        this.coordGoalX = coordGoalX;
        host.setHasTheGoal(true);
    }

    public int getCoordGoalY() {
        return coordGoalY;
    }

    public void setCoordGoalY(int coordGoalY) {
        this.coordGoalY = coordGoalY;
        host.setHasTheGoal(true);
    }

    public CoordProxy getCoordGoal() {
        coordGoal.x = getCoordGoalX();
        coordGoal.y = getCoordGoalY();
        return coordGoal;
    }

    public void setCoordGoal(CoordProxy coordGoal) {
        this.coordGoal = coordGoal;
        setCoordGoalX(coordGoal.x);
        setCoordGoalY(coordGoal.y);
    }

    public BaseObject getGoal() {
        return goal;
    }

    public void setGoal(BaseObject goal) {
        this.goal = goal;
        setCoordGoal(goal.getCoord());
    }

    public MoveTactic getMoveTactic() {
        return moveTactic;
    }

    public void setMoveTactic(MoveTactic moveTactic) {
        this.moveTactic = moveTactic;
    }

    //Вычисляем следующий шаг по направлению к цели
    public void setNextSteepCoordTowardsGoal() {
      /*  CoordProxy result = new CoordProxy();
        int i, L, xstart, ystart, xend, yend;
        float dX, dY;
        xstart = host.getCoordX();
        ystart = host.getCoordY();
        xend = getCoordGoalX();
        yend = getCoordGoalY();

        L = Math.max(Math.abs(xend - xstart), Math.abs(yend - ystart));

        if(L==0){
            dX = 0;
            dY = 0;
        }else{
            dX = (getCoordGoalX() - host.getCoordX()) / L;
            dY = (getCoordGoalY() - host.getCoordY()) / L;
        }
        host.setCoordNextX(Math.round(host.getCoordX() + dX));
        host.setCoordNextY(Math.round(host.getCoordY() + dY));*/
        CoordProxy result = moveTactic.getNextStep(host.getCoordX(),host.getCoordY(),getCoordGoalX(),getCoordGoalY()) ;
        host.setCoordNextX(result.x);
        host.setCoordNextY(result.y);
    }

    //Передвигаем объект хозяин
    public void move(){
        host.setCoordX(host.getCoordNextX());
        host.setCoordY(host.getCoordNextY());
    }


}
>>>>>>> 9e1e4fbf0332e814c4ca93ad0c6884ed9b7d5f3e
