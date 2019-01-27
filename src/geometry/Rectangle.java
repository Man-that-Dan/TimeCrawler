package geometry;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import render.Color;

public class Rectangle{
    public double x;
    public double y;
    public double width;
    public double height;
    public Polygon rect;
    public Color color = new Color(0,0,0);

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        GeometryFactory geometryFactory = new GeometryFactory();
        rect = geometryFactory.createPolygon(new Coordinate[] { new Coordinate(x, y), new Coordinate(x + width, y),
                new Coordinate(x + width, y + height), new Coordinate(x, y + height)});
    }

    public boolean intersects(Polygon polygon) {
        return rect.intersects(polygon);
    }

}
