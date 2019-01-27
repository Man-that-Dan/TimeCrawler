package world;

import entity.Entity;

public class Terrain extends Entity {
    //type property to decide what to render;
    String type;
    Rectangle poly;

    public Terrain(double x, double y){
          posx = x;
          posy = y;

          //type can be used when rendering to decide what color this terrain is, e.g.  rocks are brown
          type = "Rock";

          //generate rectangle to a max of 10 by 10
          poly = Rectangle(x, y, (Rand.room_next_rand() % 10), (Rand.room_next_rand() % 10) );
    };

    public Terrain(double x, double y, String settype){
          posx = x;
          posy = y;

          type = settype;

        //generate rectangle to a max of 10 by 10
          poly = Rectangle(x, y, (Rand.room_next_rand() % 10), (Rand.room_next_rand() % 10) );
    };


}
