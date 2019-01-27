package enemies;

import entity.*;

import event.*;
import geometry.RectangleFactory;
import org.locationtech.jts.geom.Polygon;
import world.Room;


public class Freezieboi extends Enemy {
    int damage = 5;
    int speed = 2;
    int frost_duration = 3;

    public Freezieboi(int x, int y, Room room, double diff){
        super(x, y, room, diff);
    }

    //long range horizontal freezing attack
    public void attack(){
        Polygon AoE = rf.createRectangle(this.x - 1, this.y - 1, 2, 2);
        new AttackEvent(this, AoE, attack * difficulty);
        new StatusEvent(this, AoE, new FrostStatus(frost_duration));
//        new StatusEvent(this, AoE, new StatusEffect("Freeze", 3));
    };

    public void updateAI() {
        //TODO
    }
}
