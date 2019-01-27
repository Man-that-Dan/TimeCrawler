package world;

import entity.Door;
import entity.Mob;
//import geometry.RectangleFactory;
import org.locationtech.jts.geom.Polygon;
import render.Color;

import java.util.HashSet;

import static world.Rand.room_next_double;

public class Room {
    int x;
    int y;
    public static final double width = 800;
    public static final double height = 800;
    //set of terrain objects
    public HashSet<Terrain> terrain = new HashSet<>();
    //enemies
    public HashSet<Mob> mobs = new HashSet<>();
    //doors
    public HashSet<Door> doors = new HashSet<>();

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
        generateTerrain();
        addWalls();
        return this;
    }

    public void generateDoors() {
        for(int i = 0; i < connections.length; i++) {
            if(connections[i] != null) {
                switch(i) {
                    case 0://east
                        doors.add(new Door(800, 400, this, connections[i]));
                        break;
                    case 1://north
                        doors.add(new Door(400, 800, this, connections[i]));
                        break;
                    case 2://west
                        doors.add(new Door(0, 400, this, connections[i]));
                        break;
                    case 3://south
                        doors.add(new Door(400, 0, this, connections[i]));
                        break;
                }
            }
        }
    }


    void generateTerrain() {
        for(int i = 0; i < (room_next_double() * 10); i++) {
            //TODO change this longitude to however we get user's longitude in the end
            if (gameEngine.MainGameLoop.longitude > 39.0000) {
                terrain.add(new Terrain((room_next_double() * width), (room_next_double() * height), this, "snow_mound"));
            } else {
                terrain.add(new Terrain((room_next_double() * width), (room_next_double() * height), this));
            }
        }
    }

    void addWalls() {
        terrain.add(new Terrain(0 - 10, 0, this, "wallv"));
        terrain.add(new Terrain(800 - 10, 0, this, "wallv"));
        terrain.add(new Terrain(0, 0 - 10, this, "wallh"));
        terrain.add(new Terrain(0, 800 - 10, this, "wallh"));

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
        HashSet<Mob> ret = new HashSet<>();
        for(Mob m : mobs) {
            if(m.poly.intersects(area)) ret.add(m);
        }
        return ret;
    }
}
