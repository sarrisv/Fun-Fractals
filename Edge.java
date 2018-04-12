package FractalTree;

class Edge {
    private final int x;
    private final int y;
    private final int x2;
    private final int y2;
    private final int stroke;

    public Edge(int x, int y, int x2, int y2, int stroke) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.stroke = stroke;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getStroke() {
        return stroke;
    }
}
