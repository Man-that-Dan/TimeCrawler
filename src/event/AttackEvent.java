package event;

import entity.Mob;
import gameEngine.MainGameLoop;
import org.locationtech.jts.geom.Polygon;
import render.Color;
import render.Effect;

import java.util.HashSet;

public class AttackEvent extends Event {
    Mob mob;
    Polygon areaOfEffect;
    double damage;
//    HashSet<KillEvent> killed = new HashSet<>();

    public AttackEvent(Mob attacker, Polygon areaOfEffect, double damage) {
        super();
        this.mob = attacker;
        this.generator = attacker;
        this.areaOfEffect = areaOfEffect;
        this.damage = damage;
    }

    public boolean execute() {
        Effect.effects.add(new Effect(areaOfEffect, new Color(80, 255, 80, 255), 5));
        for(Mob m : mob.room.getMobs(areaOfEffect)) {
            if(m != mob) {
                m.hurt(mob, damage);
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

