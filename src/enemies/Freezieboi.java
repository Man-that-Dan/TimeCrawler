package enemies;

import entity.*;

import event.*;
import event.Event;
import geometry.RectangleFactory;
import org.locationtech.jts.geom.Polygon;


public class Freezieboi extends Enemy {
    int damage = 5;
    int speed = 5;
    StatusEffect frostbolt = new StatusEffect("Freeze", 3);

    public Freezieboi(int x, int y, double diff){
        super(x, y, diff);
    }

    //long range horizontal freezing attack
    public void attack(){
        Polygon AoE = rf.createRectangle(this.x - 1, this.y - 1, 2, 2);
        new AttackEvent(this, AoE, attack * difficulty);
        new StatusEvent(this, AoE, new StatusEffect("Freeze", 3));
    };

    public void updateAI() {
        //TODO
    }
}
