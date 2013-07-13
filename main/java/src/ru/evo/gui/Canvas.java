package ru.evo.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 14.07.13
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */
public class Canvas extends JFrame {

    public static final String MAIN_CANVAS_TITLE = "Поле битвы";

    public Canvas() throws HeadlessException {


    }

    public void setupAndShow() {
        setTitle(MAIN_CANVAS_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 250);
        setVisible(true);
    }
}
