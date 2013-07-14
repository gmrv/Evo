package ru.evo.gui;

import ru.evo.common.Voc;
import ru.evo.core.infrastructure.MainContainer;
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

    private MainContainer mainContainer = new MainContainer();

    private final Herbivore john = new Herbivore(350, 100);
    private final Herbivore jose = new Herbivore(350, 120);

    public Canvas() throws HeadlessException {
        setup();

        mainContainer.add(john);
        mainContainer.add(jose);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        mainContainer.sendWakeUp();
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
        g.setColor(Color.BLUE);
        g.fillRect(20, 40, 300, 300);

        g.setColor(Color.GREEN);
        g.fillRect(400, 40, 300, 300);

        john.setWord(g);
        jose.setWord(g);

        mainContainer.paint();

        Graphics2D g2=(Graphics2D) g;
        double pi2=6.28;
        int r=100;
        for (double a=0;a<pi2;a=a+0.01){
            int  x= (int) (350 + r*Math.cos(a));
            int y= (int) (190+r*Math.sin(a));
           g2.drawRect(x,y,1,1);
        }
    }

}
