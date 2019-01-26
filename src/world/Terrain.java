package world;

import entity.Entity;

public class Terrain extends Entity {
    //type property to decide what to render;
    String type;

    public Terrain(double x, double y){
      posx = x;
      posy = y;

      type = "Rock";
    };


}
