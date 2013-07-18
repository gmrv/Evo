package ru.evo.gui;

import ru.evo.common.Voc;
import ru.evo.core.infrastructure.MainContainer;
import ru.evo.core.objects.Grass;
import ru.evo.core.objects.Herbivore;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 14.07.13
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */
public class Canvas extends JFrame {



    private final Herbivore john = new Herbivore(350, 100);


    public Canvas() throws HeadlessException {
        setup();

        Voc.mainContainer.add(john);

        for(int i = 0; i<100; i++){

            Herbivore hbvr = new Herbivore(
                    Voc.getRand().nextInt(800),
                    Voc.getRand().nextInt(800)
            );

            hbvr.setCoordGoalX(100);
            hbvr.setCoordGoalY(100);

            Voc.mainContainer.add(hbvr);
        }

        for(int i = 0; i<200; i++){
            Voc.mainContainer.add(new Grass(
                    Voc.getRand().nextInt(800),
                    Voc.getRand().nextInt(800)
            ));
        }


        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                        Voc.mainContainer.sendWakeUp();
                        repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        };

        setVisible(true);

        Thread t = new Thread(r);
        t.start();

    }

    public void setup() {
        setTitle(Voc.MAIN_CANVAS_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Voc.MAIN_CANVAS_WIDTH, Voc.MAIN_CANVAS_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Voc.g = g;

        g.setColor(Color.BLACK);
        g.drawOval(99,99,3,3);

        Voc.mainContainer.sendPaint();

    }

}
