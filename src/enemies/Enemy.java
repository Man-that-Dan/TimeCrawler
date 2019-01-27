package enemies;

import event.*;

import entity.*;
import geometry.Rectangle;
import org.locationtech.jts.geom.Polygon;

//enemy base class - abstract class
abstract public class Enemy extends Mob {
    double difficulty;
    int damage = 5;
    int speed = 5;

    public void attack(){
        Rectangle AoE = new Rectangle(this.poly.x, this.poly.y, (this.poly.x + 5), (this.poly.y + 5)  );
        Event attacked = new AttackEvent(this, AoE, attack * difficulty);
    };

}