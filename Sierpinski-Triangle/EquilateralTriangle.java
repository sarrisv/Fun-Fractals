package STriangle;

import java.awt.*;

class EquilateralTriangle {
    public Polygon constructTriangle(double x, double y, double r) {
        return new Polygon(
                new int[]{(int) (x - (.5 * r * Math.sqrt(3))), (int) x, (int) (x + (.5 * r * Math.sqrt(3)))},
                new int[]{(int) (y + (.5 * r)), (int) (y - r), (int) (y + (.5 * r))},
                3);
    }
}
