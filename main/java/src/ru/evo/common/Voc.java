package ru.evo.common;

import ru.evo.core.infrastructure.MainContainer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 14.07.13
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 */
//todo:Синглтон
public class Voc {

    private static Random rand = new Random(System.currentTimeMillis());

    public static final String MAIN_CANVAS_TITLE = "Поле битвы";
    public static final int MAIN_CANVAS_WIDTH = 800;
    public static final int MAIN_CANVAS_HEIGHT = 800;

    public static final int HERBIVORE_FIND_RADIUS = 50;

    public static Graphics g;

    public static BufferedImage img;

    //контейнер для всего сущего
    public static  MainContainer mainContainer = new MainContainer();

    private static int evoObjectCounter;

    public enum evoObjects { WATER, GRASS, HERBIVORE, CARNIVORE }

    public static Random getRand() {
        return rand;
    }

    //Генерация id объекта в формате NNNNNNNN
    public static String getNewId(){
        String result =  Integer.toString(evoObjectCounter++);
        while(result.length()<8){
            result = "0" + result;
        }
        return result;
    }

    public static void writeLog(String message){
        System.out.println(message);
    }

    public static synchronized MainContainer getMainContainer() {
        return mainContainer;
    }

    public static void setMainContainer(MainContainer mainContainer) {
        Voc.mainContainer = mainContainer;
    }
}
