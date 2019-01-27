package enemies;

import entity.Entity;
import event.AttackEvent;
import event.Event;
import geometry.Rectangle;
import org.locationtech.jts.geom.Polygon;

public class BigMonster extends Enemy {


    int damage = 15;
    int speed = 1;
    public BigMonster(int x, int y, double diff){

        this.poly.x = x;
        this.poly.y = y;
        this.poly.width = 10;
        this.poly.height = 10;
        difficulty = diff;
    }

    //Big AoE attack
    public void attack(){
        Rectangle AoE = new Rectangle(this.poly.x, this.poly.y, (this.poly.x + 30), (this.poly.y + 30)  );
        Event attacked = new AttackEvent(this, AoE, attack * difficulty);
    };
}
