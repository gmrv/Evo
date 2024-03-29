package ru.evo.gui;

import ru.evo.common.Voc;
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

    Image buffer = null;

    public Canvas() throws HeadlessException {
//        try {
//            Voc.img = ImageIO.read(new File("C:\\1.png"));
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        setupCanvas();
        createPopulation();
        createLandscape();
        startLive();
        setVisible(true);
    }

    public void setupCanvas() {
        setTitle(Voc.MAIN_CANVAS_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Voc.MAIN_CANVAS_WIDTH, Voc.MAIN_CANVAS_HEIGHT);
        this.getContentPane().setBackground(Color.BLACK);
    }

    private void createPopulation() {
        for (int i = 0; i < 100; i++) {
            Herbivore hbvr = new Herbivore(
                    Voc.getRand().nextInt(800),
                    Voc.getRand().nextInt(800)
            );
            //hbvr.setCoordGoalX(Voc.getRand().nextInt(800));
            //hbvr.setCoordGoalY(Voc.getRand().nextInt(800));
            Voc.mainContainer.add(hbvr);
        }
    }

    private void createLandscape() {
        for (int i = 0; i < 100; i++) {
            Voc.mainContainer.add(new Grass(
                    Voc.getRand().nextInt(800),
                    Voc.getRand().nextInt(800),
                    Voc.getRand().nextInt((int) Grass.PULSE_PEAK_FOR_GRASS)
            ));
        }
    }

    private void startLive() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Voc.getMainContainer().dropDieObject();
                        TimeUnit.MILLISECONDS.sleep(50);
                        Voc.getMainContainer().sendWakeUp();
                        repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        //if(buffer==null) //посмотреть треки
        buffer = createImage(800, 800);
        Graphics screengc = null;
        screengc = g;
        g = buffer.getGraphics();
        g.fillRect(0, 0, 800, 800);

        //super.paint(g);
        Voc.g = g;
        Voc.mainContainer.sendPaint();

        screengc.drawImage(buffer, 0, 0, null);
    }
}
