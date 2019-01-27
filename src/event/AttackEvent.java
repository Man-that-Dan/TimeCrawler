package event;

import entity.Entity;
import org.locationtech.jts.geom.Polygon;

public class AttackEvent extends Event {
    Entity entity;
    Polygon areaOfEffect;
    double damage;

    public AttackEvent(Entity e, Polygon areaOfEffect, double damage) {
        super();
        this.entity = e;
        this.areaOfEffect = areaOfEffect;
        this.damage = damage;
    }

    public boolean execute() {

    }
    public boolean revert() {

    }
}
