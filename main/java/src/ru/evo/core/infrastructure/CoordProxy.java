package ru.evo.core.infrastructure;

//Класс позволяет более удобно работать с координатами точки игрового поля.
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
        return this.x == b.x && this.y == b.y;
    }
}
