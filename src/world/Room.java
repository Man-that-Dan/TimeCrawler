package world;

import entity.Entity;

import java.util.HashSet;

public class Room {
    //set of terrain objects
    HashSet<Terrain> terrain;
    //enemies
    HashSet<Entity> mobs;
    Room[] neighbors = new Room[] {null, null, null, null};
    //0: East (+x)
    //1: North (+y)
    //2: West (-x)
    //3: South (-y)


    public Room(int width, int height) {
//TODO
    //use x and y as height and width to
    for(int i = 0; i < (Rand.room_rand() % 10); i++){
        if(longitude > 39.0000){
            terrain.add(new Terrain((Rand.room_rand % w),(Rand.room_rand % h), "snow_mound"));
        } else {
            terrain.add(new Terrain((Rand.room_rand % w),(Rand.room_rand % h)));
        };
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
