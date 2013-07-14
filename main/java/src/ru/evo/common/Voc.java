package ru.evo.common;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: GUMEROV_SHF
 * Date: 14.07.13
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 */
public class Voc {

    private static Random rand = new Random(System.currentTimeMillis());

    public static final String MAIN_CANVAS_TITLE = "Поле битвы";
    public static final int MAIN_CANVAS_WIDTH = 800;
    public static final int MAIN_CANVAS_HEIGHT = 800;

    public static Random getRand() {
        return rand;
    }
}
