package event;

import entity.Mob;
import org.locationtech.jts.geom.Polygon;

import java.util.HashSet;

public class AttackEvent extends Event {
    Mob mob;
    Polygon areaOfEffect;
    double damage;
//    HashSet<KillEvent> killed = new HashSet<>();

    public AttackEvent(Mob attacker, Polygon areaOfEffect, double damage) {
        super();
        this.mob = attacker;
        this.areaOfEffect = areaOfEffect;
        this.damage = damage;
    }

    public boolean execute() {
        for(Mob m : mob.room.getMobs(areaOfEffect)) {
            if(m != mob) {
                KillEvent ke = m.hurt(mob, damage);
//                if(ke != null){
//                    killed.add(ke);
//                }
            }
        }
        return true;
    }
    public boolean revert() {
        //Assume that all kill events originally resulting from this event have been reverted before
        //this event is reverted, so the mobs will get healed appropriately
        for(Mob m : mob.room.getMobs(areaOfEffect)) {
            if(m != mob) {
                m.heal(damage);
            }
        }
        return true;
    }
}

