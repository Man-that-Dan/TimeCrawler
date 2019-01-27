package entity;


import event.KillEvent;
import event.MoveEvent;
import org.locationtech.jts.algorithm.Centroid;
import org.locationtech.jts.geom.Coordinate;
import world.Room;

import java.util.HashSet;
import java.util.PriorityQueue;

public abstract class Mob extends Entity {
    public boolean spawned = false;
    public double attack;
    public double health;
    public double speed;
    public int direction;
    public double difficulty;
    public PriorityQueue<StatusEffect> statusEffects = new PriorityQueue<>();

    public Mob(double x, double y, Room room) {
        super(x, y, room);
    }


    public void forceMovement(double dx, double dy) {
        MoveEvent me = new MoveEvent(this, dx, dy);
    }
    public KillEvent hurt(Mob source, double damage) {
        if(this.health <= 0) {
            this.health -= damage;
            return null;
        } else {
            this.health -= damage;
            if(this.health <= 0) {
                return new KillEvent(this, source);
            }
            return null;
        }
    }

    public void heal(double healing) {
        this.health += healing;
    }

    public abstract void updateAI();

    public Coordinate getCenter() {
        return Centroid.getCentroid(poly);
    }

    public void applyStatus(StatusEffect e) {
        boolean alreadyHere = false;
        for(StatusEffect se : statusEffects) {
            if(e.equals(se)) {
                alreadyHere = true;
                se.expiration_timer += e.expiration_timer;
                break;
            }
        }
        if(!alreadyHere) {
            e.applyToMob(this);
        }
    }

    public void tickStatuses() {
        while(!statusEffects.isEmpty()){
            if(statusEffects.peek().expiration_timer == 0) statusEffects.poll().removeFromMob(this);
            else break;
        }
        for(StatusEffect statusEffect : statusEffects) {
            statusEffect.expiration_timer--;
        }
    }
}
