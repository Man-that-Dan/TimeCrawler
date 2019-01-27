package world;

import entity.Mob;
//import geometry.RectangleFactory;
import org.locationtech.jts.geom.Polygon;
import render.Color;

import java.util.HashSet;

import static world.Rand.room_next_double;

public class Room {
    int x;
    int y;
    public static int width = 100;
    public static int height = 100;
    //set of terrain objects
    public HashSet<Terrain> terrain = new HashSet<>();
    //enemies
    public HashSet<Mob> mobs = new HashSet<>();

    //0: East (+x)
    //1: North (+y)
    //2: West (-x)
    //3: South (-y)
    //4 north east (+x +y)
    //5 north west (-x +y)
    //6 south west (-x -y)
    //7 south east  (+x -y)
    Room[] connections = new Room[] {null, null, null, null};
    Polygon[] simpleWalls;
    Color[] simpleWallColors;


    public Room(int x, int y) {
        this.x = x;
        this.y = y;
//        RectangleFactory rf = new RectangleFactory();
//        simpleWalls = new Polygon[4];
//        simpleWalls[0] = rf.createRectangle(x+0.85, y, 0.15, 1.0);
//        simpleWalls[1] = rf.createRectangle(x, y + 0.85, 1.0, 0.15);
//        simpleWalls[2] = rf.createRectangle(x, y, 0.15, 1.0);
//        simpleWalls[3] = rf.createRectangle(x, y, 1.0, 0.15);
//        for(int i = 0; i < 4; i++) {
//            simpleWallColors[i] = new Color(255, 255, 255);
//        }
    }

    public Room init() {
        for(int i = 0; i < (room_next_double() * 10); i++) {
            //TODO change this longitude to however we get user's longitude in the end
            if (gameEngine.MainGameLoop.longitude > 39.0000) {
                terrain.add(new Terrain((room_next_double() * width), (room_next_double() * height), this, "snow_mound"));
            } else {
                terrain.add(new Terrain((room_next_double() * width), (room_next_double() * height), this));
            }
        }
        return this;
    }

    boolean setConnection(Room newNeighbor, int pos) {
        //If the connection doesn't already exists for this room in the specified direction, and if
        //the connection doesn't already exist for the specified room in the opposite direction
        if(connections[pos] == null && newNeighbor.connections[(pos + 2) % 4] == null) {
            //Make the rooms neighbors
            connections[pos] = newNeighbor;
            newNeighbor.connections[(pos + 2) % 4] = this;
            simpleWallColors[pos] = new Color(0, 0, 0);
            //return success
            return true;
        } else {
            //return failure
            return false;
        }
    }

    public HashSet<Mob> getMobs(Polygon area) {
        //TODO
        return null;
    }
}
