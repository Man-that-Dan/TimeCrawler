package enemies;

import entity.*;

import event.*;
import org.locationtech.jts.geom.Polygon;
import render.Color;
import world.Rand;
import world.Room;

public class Freezieboi extends Enemy {
    int frost_duration = 50;
    int ticksSinceLastAttack = 0;
    boolean hasRespondedToMovementFailure = false;

    public Freezieboi(double x, double y, Room room, double diff){
        super(x, y, room, diff);
        this.poly = rf.createRectangle(x - 4, y - 4, 8, 8);
        this.color = new Color(100,100,255);
        health = 80;
        attack = 5;
        speed = 2;
    }

    //long range horizontal freezing attack
    public void attack(){
        Polygon AoE = rf.createRectangle(this.x - 8, this.y - 8, 16, 16);
        new AttackEvent(this, AoE, attack * difficulty);
        new StatusEvent(this, AoE, new FrostStatus(frost_duration));
//        new StatusEvent(this, AoE, new StatusEffect("Freeze", 3));
    };

    public void updateAI() {
        hasRespondedToMovementFailure = false;
        Mob target = null;
        for(Mob m : room.mobs) {
            if(m instanceof Player) {
                target = (Mob) m;
                break;
            }
        }
        if(target == null) target = this;
        double dx = target.x - x;
        double dy = target.y - y;
        if(dx == 0 && dy == 0) {

        } else {
            new MoveEvent(this, speed * dx / Math.sqrt((dx * dx + dy * dy)), speed * dy / Math.sqrt((dx * dx + dy * dy)));
        }


        if(ticksSinceLastAttack >= 10) {
            ticksSinceLastAttack = 0;
            attack();
        }
        ticksSinceLastAttack++;
    }

    @Override
    public void respond(Event e) {
        if(e instanceof MoveEvent) {
            MoveEvent me = (MoveEvent) e;
            if(me.moved_x == 0 && me.moved_y == 0 && !hasRespondedToMovementFailure) {
                hasRespondedToMovementFailure = true;
                double dx = Rand.mob_next_double(-1, 1);
                double dy = Rand.mob_next_double(-1, 1);
                new MoveEvent(this, dx * speed / Math.sqrt(dx * dx + dy * dy), dy * speed / Math.sqrt(dx * dx + dy * dy));
            }
        }
    }
}
