package entity;

import event.Event;
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
    public Color color = new Color(0, 0, 0);
    public Room room;

    public Entity(double x, double y, Room room) {
        this.x = x;
        this.y = y;
        this.room = room;
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
        int ph = poly.hashCode();
//        int rh = room.hashCode();
//        return ph ^ rh;
        return ph;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Entity) {
            Entity other = (Entity) o;
            return this.poly.equals(other.poly) && this.color.equals(other.color) && this.room.equals(other.room);
        }
        return false;
    }

    public void respond(Event e) {

    }
}
