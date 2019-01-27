package world;

import entity.Entity;
import geometry.Rectangle;

import java.util.HashSet;

import static world.Rand.room_next_double;

public class Room {
    int x;
    int y;
    public static int width = 100;
    public static int height = 100;
    //set of terrain objects
    HashSet<Terrain> terrain;
    //enemies
    HashSet<Entity> mobs;

    //0: East (+x)
    //1: North (+y)
    //2: West (-x)
    //3: South (-y)
    Room[] connections = new Room[] {null, null, null, null};
    Rectangle[] simpleWalls;


    public Room(int x, int y) {
        this.x = x;
        this.y = y;
        for(int i = 0; i < (room_next_double() * 10); i++) {
            if (longitude > 39.0000) {
                terrain.add(new Terrain((Rand.room_rand % w), (Rand.room_rand % h), "snow_mound"));
            } else {
                terrain.add(new Terrain((Rand.room_rand % w), (Rand.room_rand % h)));
            }
        }
        simpleWalls = new Rectangle[4];
        simpleWalls[0] = new Rectangle(x, y)
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
