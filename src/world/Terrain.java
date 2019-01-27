package world;

import entity.Entity;
import geometry.RectangleFactory;

public class Terrain extends Entity {
    //type property to decide what to render;
    String type;

    public Terrain(double x, double y, Room room){
        this(x,y, room, "Rock");
    };

    public Terrain(double x, double y, Room room, String settype){
        super(x, y, room);
        RectangleFactory rf = new RectangleFactory();
        this.poly = rf.createRectangle(x, y, Rand.room_next_double() * 10, Rand.room_next_double() * 10);
        //type can be used when rendering to decide what color this terrain is, e.g.  rocks are brown
        //type can be used when rendering to decide what color this terrain is, e.g.  rocks are brown
        type = settype;
    };


}
