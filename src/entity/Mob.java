package entity;


import event.KillEvent;
import event.MoveEvent;
import org.locationtech.jts.algorithm.Centroid;
import org.locationtech.jts.geom.Coordinate;

import java.util.HashSet;

public abstract class Mob extends Entity {
    public double attack;
    public double health;
    public int speed;
    public int direction;
    public HashSet<StatusEffect> statusEffects = new HashSet<>();


    public void forceMovement(double dx, double dy) {
        MoveEvent me = new MoveEvent(this, dx, dy);
    }
    public KillEvent hurt(Mob source, double damage) {
        if(this.health <= 0) {
            this.health -= damage;
            return null;
        } else {
            this.health -= damage;
            if (this.health <= 0) {
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

    }
}
