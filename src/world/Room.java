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
        //If the connection doesn't already exists for this room in the specified direction, and if
        //the connection doesn't already exist for the specified room in the opposite direction
        if(connections[pos] == null && newNeighbor.connections[(pos + 2) % 4] == null) {
            //Make the rooms neighbors
            connections[pos] = newNeighbor;
            newNeighbor.connections[(pos + 2) % 4] = this;
            //return success
            return true;
        } else {
            //return failure
            return false;
        }
    }
}
