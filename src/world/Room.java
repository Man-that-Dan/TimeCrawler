package world;

import entity.Entity;

import java.util.HashSet;

public class Room {
    HashSet<Terrain> terrain;
    HashSet<Entity> mobs;
    Room[] connections = new Room[] {null, null, null, null};
    //0: East (+x)
    //1: North (+y)
    //2: West (-x)
    //3: South (-y)


    public Room(int x, int y) {
//TODO
    }

    boolean setConnection(Room newNeighbor, int pos) {
        if(connections[pos] == null && newNeighbor.connections[(pos + 2) % 4] == null) {
            connections[pos] = newNeighbor;
            newNeighbor.connections[(pos + 2) % 4] = this;
            return true;
        } else {
            return false;
        }
    }
}
