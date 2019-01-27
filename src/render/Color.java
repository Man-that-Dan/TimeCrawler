package render;

public class Color {
    public int r;
    public int g;
    public int b;
    public int a;

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }
    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
