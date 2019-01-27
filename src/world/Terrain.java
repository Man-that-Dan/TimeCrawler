package world;

import entity.Entity;
import geometry.Rectangle;

public class Terrain extends Entity {
    //type property to decide what to render;
    String type;

    public Terrain(double x, double y){
        this.poly.x = x;
        this.poly.y = y;
        this.poly.width = Rand.room_next_double() * 10;
        this.poly.width = Rand.room_next_double() * 10;

        //type can be used when rendering to decide what color this terrain is, e.g.  rocks are brown
        type = "Rock";
    };

    public Terrain(double x, double y, String settype){
        this.poly.x = x;
        this.poly.y = y;
        this.poly.width = Rand.room_next_double() * 10;
        this.poly.width = Rand.room_next_double() * 10;

        //type can be used when rendering to decide what color this terrain is, e.g.  rocks are brown
        type = settype;
    };


}
