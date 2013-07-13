package ru.evo.gui;

import ru.evo.common.Voc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 14.07.13
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */
public class Canvas extends JFrame {

    public Canvas() throws HeadlessException {
        setup();
        setVisible(true);

    }

    public void setup() {
        setTitle(Voc.MAIN_CANVAS_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Voc.MAIN_CANVAS_WIDTH, Voc.MAIN_CANVAS_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.
        g.setColor(Color.BLUE);
        g.fillRect(20, 40, 300, 300);

        g.setColor(Color.GREEN);
        g.fillRect(400, 40, 300, 300);

        g.setColor(Color.RED);
        g.drawOval(350,100,1,1);

        Graphics2D g2=(Graphics2D) g;
        double pi2=6.28;
        int r=5;
        for (double a=0;a<pi2;a=a+0.1){
            int  x= (int) (350 + r*Math.cos(a));
            int y= (int) (190+r*Math.sin(a));
           g2.drawRect(x,y,1,1);
        }
//        g2.draw(new Line2D.Double(360, 150, 380, 170));

    }

}
