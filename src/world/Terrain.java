package world;

import entity.Entity;

public class Terrain extends Entity {
    //type property to decide what to render;
    String type;
    Rectangle poly;

    public Terrain(double x, double y){
      posx = x;
      posy = y;

      type = "Rock";
      poly = Rectangle(x, y, )
    };

    public Terrain(double x, double y, String settype){
        posx = x;
        posy = y;

        type = settype;
    };


}
