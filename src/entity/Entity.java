package entity;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateFilter;
import org.locationtech.jts.geom.Polygon;
import render.Color;
import world.Room;
import world.Terrain;

import java.util.HashSet;

public abstract class Entity {
//    public Rectangle poly;
    public double x;
    public double y;
    public Polygon poly;
    public Color color;
    public Room room;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void transpose(double dx, double dy) {
        CoordinateFilter transposer = new CoordinateFilter() {
            @Override
            public void filter(Coordinate coord) {
                coord.x += dx;
                coord.y += dy;
            }
        };
        poly.apply(transposer);
        poly.geometryChanged();
        x += dx;
        y += dy;
        //TODO: CHECK THIS
    }

    public HashSet<Terrain> getTerrainCollisions() {
        HashSet<Terrain> ret = new HashSet<>();
        for(Terrain t : room.terrain) {
            if(t.poly.intersects(poly)) {
                ret.add(t);
            }
        }
        return ret;
    }
    public HashSet<Entity> getMobCollisions() {
        //TODO
        return null;
    }

    public Coordinate[] getRenderInformation() {
        return poly.getCoordinates();
    }

    @Override
    public int hashCode() {
        return poly.hashCode() ^ room.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Entity) {
            Entity other = (Entity) o;
            return this.poly.equals(other.poly) && this.color.equals(other.color) && this.room.equals(other.room);
        }
        return false;
    }
}
