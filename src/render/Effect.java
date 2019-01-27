package render;


import org.locationtech.jts.geom.Polygon;

public class Effect {
    public Polygon poly;
    public Color color;

    public Effect(Polygon polygon, Color color) {
        this.poly = polygon;
        this.color = color;
    }
}
