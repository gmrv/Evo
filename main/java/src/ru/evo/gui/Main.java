package ru.evo.gui;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 14.07.13
 * Time: 0:25
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        Canvas canvas = new Canvas();
    }
}
