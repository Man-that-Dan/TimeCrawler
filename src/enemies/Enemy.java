package enemies;


import entity.Entity;
import geometry.Rectangle;

//enemy base class - abstract class
public class Enemy extends Entity {
    double difficulty;
    int damage = 5;
    int speed = 5;

    public void attack(){
        Rectangle AoE = new Rectangle(this.posx, this.posy, (this.poly.x + 5), (this.poly.y + 5)  );
        //TODO create event with this AoE to see if it intersects the player
    };

}