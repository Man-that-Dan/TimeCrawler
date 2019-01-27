package entity;

import geometry.Rectangle;
import world.Room;
import world.Terrain;

import java.util.HashSet;

public abstract class Entity {
    public Rectangle poly;
    public Room room;

    public void transpose(double dx, double dy) {
        poly.x += dx;
        poly.y += dy;
    }

    public HashSet<Terrain> getTerrainCollisions() {
        //TODO
        return null;
    }
    public HashSet<Entity> getMobCollisions() {
        //TODO
        return null;
    }

    public abstract float[] getRenderInformation();
}
