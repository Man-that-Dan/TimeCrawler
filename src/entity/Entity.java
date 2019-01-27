package entity;

import geometry.Rectangle;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateFilter;
import org.locationtech.jts.geom.Polygon;
import render.Color;
import world.Room;
import world.Terrain;

import java.util.HashSet;

public abstract class Entity {
//    public Rectangle poly;
    public Polygon poly;
    public Color color;
    public Room room;

    public void transpose(double dx, double dy) {
        CoordinateFilter transposer = new CoordinateFilter() {
            @Override
            public void filter(Coordinate coord) {
                coord.x += dx;
                coord.y += dy;
            }
        };
        poly.apply(transposer);
        //TODO: CHECK THIS
    }

    public HashSet<Terrain> getTerrainCollisions() {
        //TODO
        return null;
    }
    public HashSet<Entity> getMobCollisions() {
        //TODO
        return null;
    }

    public Coordinate[] getRenderInformation() {
        return poly.getCoordinates();
    }
}
