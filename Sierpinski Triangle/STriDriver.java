package STriangle;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class STriDriver extends Applet implements Runnable {
    private final Dimension dim = new Dimension(750, 750);
    private final ArrayList<Polygon> pList = new ArrayList<>(10);
    private final EquilateralTriangle equilateralTriangle = new EquilateralTriangle();
    private Graphics g2;
    private Image offscreen;

    public void init() {
        setSize((int) dim.getWidth(), (int) dim.getHeight());
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
        offscreen = createImage((int) dim.getWidth(), (int) dim.getHeight());
        g2 = offscreen.getGraphics();
    }

    public void start() {
        var th = new Thread(this);
        th.start();
    }

    public void stop() {

    }

    public void paint(Graphics g) {
        g2.clearRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());

        var base = equilateralTriangle.constructTriangle(375, 450, 400);

        g2.fillPolygon(base);
        g2.setColor(Color.WHITE);

        fractal(base, 0);

        for (var i : pList) {
            g2.drawPolygon(i);
        }
        g.drawImage(offscreen, 0, 0, this);
    }

    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        while (true) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException ignored) {

            }
        }
    }

    private int findMidpoint(int subOne, int subTwo) {
        System.out.println("Midpoint: (" + subOne + ", " + subTwo + ") = " + ((subOne + subTwo) / 2));
        return (subOne + subTwo) / 2;
    }

    private void fractal(Polygon p, int depth) {
        pList.add(p);
        if (depth < 7) {
            System.out.println("P Cords:  (" + p.xpoints[0] + ", " + p.ypoints[0] + ")\t, (" + p.xpoints[1] + ", " + p.ypoints[1] + ")\t, (" + p.xpoints[2] + ", " + p.ypoints[2] + ")");
            var t = new Polygon(
                    new int[]{
                            (findMidpoint(p.xpoints[0], p.xpoints[1])),
                            (findMidpoint(p.xpoints[1], p.xpoints[2])),
                            (findMidpoint(p.xpoints[0], p.xpoints[2]))},
                    new int[]{
                            (findMidpoint(p.ypoints[0], p.ypoints[1])),
                            (findMidpoint(p.ypoints[1], p.ypoints[2])),
                            (findMidpoint(p.ypoints[0], p.ypoints[2]))},
                    3);
            System.out.println("T Cords:  (" + t.xpoints[0] + ", " + t.ypoints[0] + ")\t, (" + t.xpoints[1] + ", " + t.ypoints[1] + ")\t, (" + t.xpoints[2] + ", " + t.ypoints[2] + ")");
            pList.add(t);
            //Triangle 1
            fractal(
                    new Polygon(
                            new int[]{p.xpoints[0], t.xpoints[0], t.xpoints[2]},
                            new int[]{p.ypoints[0], t.ypoints[0], t.ypoints[2]},
                            3), depth + 1);
            //Triangle 2
            fractal(
                    new Polygon(
                            new int[]{t.xpoints[0], p.xpoints[1], t.xpoints[1]},
                            new int[]{t.ypoints[0], p.ypoints[1], t.ypoints[1]},
                            3), depth + 1);
            //Triangle 3
            fractal(
                    new Polygon(
                            new int[]{t.xpoints[2], t.xpoints[1], p.xpoints[2]},
                            new int[]{t.ypoints[2], t.ypoints[1], p.ypoints[2]},
                            3), depth + 1);

        }
    }
}
