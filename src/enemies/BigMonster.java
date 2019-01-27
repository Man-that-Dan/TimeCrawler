package enemies;

import entity.Mob;
import event.AttackEvent;
import event.Event;
import geometry.RectangleFactory;
import org.locationtech.jts.geom.Polygon;
import render.Color;
import world.Room;

public class BigMonster extends Enemy {

    RectangleFactory rf = new RectangleFactory();
    public BigMonster(double x, double y, Room room, double diff){
        super(x, y, room, diff);
        this.poly = rf.createRectangle(x - 5, y - 5, 10, 10);
        attack = 15;
        speed = 1;
        this.color = new Color(255, 0, 0);
    }

    //Big AoE attack
    public void attack(){
        Polygon AoE = rf.createRectangle(this.x - 15, this.y - 15, 30, 30);
        Event attacked = new AttackEvent(this, AoE, attack * difficulty);
    };

    public void updateAI() {
        //TODO
    }
}
