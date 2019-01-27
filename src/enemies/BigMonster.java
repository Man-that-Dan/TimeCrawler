package enemies;

import entity.Mob;
import entity.Player;
import event.AttackEvent;
import event.Event;
import event.MoveEvent;
import geometry.RectangleFactory;
import org.locationtech.jts.geom.Polygon;
import render.Color;
import world.Rand;
import world.Room;

public class BigMonster extends Enemy {

    int ticksSinceLastAttack = 0;

    boolean hasRespondedToMovementFailure = false;
    RectangleFactory rf = new RectangleFactory();
    public BigMonster(double x, double y, Room room, double diff){
        super(x, y, room, diff);
        this.poly = rf.createRectangle(x - 5, y - 5, 10, 10);
        attack = 15;
        health = 20;
        speed = 1;
        this.color = new Color(255, 0, 0);
    }

    //Big AoE attack
    public void attack(){
        Polygon AoE = rf.createRectangle(this.x - 15, this.y - 15, 30, 30);
        new AttackEvent(this, AoE, attack * difficulty);
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
