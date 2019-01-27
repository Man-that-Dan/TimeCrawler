package enemies;

import event.*;

import entity.*;
import geometry.RectangleFactory;
import org.locationtech.jts.geom.Polygon;
import world.Room;

//enemy base class - abstract class
abstract public class Enemy extends Mob {
    double difficulty;
    RectangleFactory rf = new RectangleFactory();

    public Enemy(double x, double y, Room room, double difficulty) {
        super(x, y, room);
        this.difficulty = difficulty;
        this.health = 10;
    }

    public void attack(){
        Polygon AoE = rf.createRectangle(this.x - 2.5, this.y - 2.5, 5, 5);
        Event attacked = new AttackEvent(this, AoE, attack * difficulty);
    };

}