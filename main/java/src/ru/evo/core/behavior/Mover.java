package ru.evo.core.behavior;

import ru.evo.core.infrastructure.CoordProxy;
import ru.evo.core.objects.BaseObject;
import ru.evo.core.objects.LiveObject;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 05.08.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class Mover {

    //todo: Реализовать его как посредника, который будет оперировать с данными host.
    //Убрать все данные из этого класса. Тут только логика.

    private LiveObject host;
    private BaseObject goal;
    private int coordGoalX;
    private int coordGoalY;
    private CoordProxy coordGoal;

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

    public void setNextSteepCoordTowardsGoal() {
        CoordProxy result = new CoordProxy();
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
        host.setCoordNextY(Math.round(host.getCoordY() + dY));
    }

    public void move(){
        host.setCoordX(host.getCoordNextX());
        host.setCoordY(host.getCoordNextY());
    }
}
