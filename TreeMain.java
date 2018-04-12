package FractalTree;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class TreeMain extends Applet implements Runnable {

    private final Dimension dim = new Dimension(900, 750);
    private final ArrayList<Edge> list = new ArrayList<>(10);
    private double timer = 0;

    public void init() {
        setSize((int) dim.getWidth(), (int) dim.getHeight());
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
    }

    public void start() {
        var th = new Thread(this);
        th.start();
    }

    public void stop() {

    }

    public void paint(Graphics g) {
        var g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(7));
        var x = 450 + 3 * Math.cos(timer);
        var y = 600 - Math.sin(timer);
        g2.drawLine(450, 700, (int) x, (int) y);
        list.clear();
        fractal(0, -90, 100, x, y);
        for (var i : list) {
            g2.setStroke(new BasicStroke(7 - i.getStroke()));
            g2.drawLine(i.getX(), i.getY(), i.getX2(), i.getY2());
        }
    }

    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        while (true) {
            try {
                repaint();
                timer += .2;
                Thread.sleep(85);
            } catch (InterruptedException ignored) {

            }
        }
    }

    private void fractal(int depth, double angle, double length, double x, double y) {
        if (depth < 7) {
            var x2 = (x + Math.cos(Math.toRadians(angle)) * length) + Math.cos(timer) * depth * 2;
            var y2 = (y + Math.sin(Math.toRadians(angle)) * length) + Math.sin(timer);
            list.add(new Edge((int) x, (int) y, (int) x2, (int) y2, depth));
            fractal(depth + 1, angle + 30, length * .9, x2, y2);
            fractal(depth + 1, angle - 30, length * .9, x2, y2);
        }
    }
}