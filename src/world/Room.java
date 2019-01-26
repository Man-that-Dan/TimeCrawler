package world;

import entity.Entity;

import java.util.HashSet;

public class Room {
    HashSet<Terrain> terrain;
    HashSet<Entity> mobs;
    Room[] neighbors = new Room[] {null, null, null, null};
    //0: East (+x)
    //1: North (+y)
    //2: West (-x)
    //3: South (-y)


    public Room(int x, int y) {
//TODO
    }

    boolean setNeighbor(Room newNeighbor, int pos) {
        if(neighbors[pos] == null && newNeighbor.neighbors[(pos + 2) % 4] == null) {
            neighbors[pos] = newNeighbor;
            newNeighbor.neighbors[(pos + 2) % 4] = this;
            return true;
        } else {
            return false;
        }
    }
}
