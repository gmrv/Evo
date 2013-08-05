package ru.evo.core.infrastructure;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 05.08.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class CoordProxy {
    public int x;
    public int y;

    public CoordProxy(){
        x=-1;
        y=-1;
    }

    public CoordProxy(int aX, int aY){
        x=aX;
        y=aY;
    }

    public boolean equals (CoordProxy b){
        if(this.x == b.x && this.y == b.y){
            return true;
        }else{
            return false;
        }
    }
}
