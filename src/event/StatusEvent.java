
package event;

import entity.*;
import org.locationtech.jts.geom.Polygon;

public class StatusEvent extends Event {
    Mob source;
    Mob singleTarget;
    Polygon areaOfEffect;
    StatusEffect effect;

    public StatusEvent(Mob source, Polygon areaOfEffect, StatusEffect s) {
        super();
        //TODO assign this to player character
        this.source = source;
        this.areaOfEffect = areaOfEffect;
        this.effect = s;
        this.singleTarget = null;
    }

    public StatusEvent(Mob source, Mob target, StatusEffect s) {
        super();
        this.source = source;
        this.singleTarget = target;
        this.effect = s;
        this.areaOfEffect = null;
    }

    public boolean execute() {
        if(this.areaOfEffect != null) {
            for (Mob m : source.room.getMobs(areaOfEffect)) {
                if (m != source) {
                    m.applyStatus(effect);
                }
            }
        }
        if(this.singleTarget != null) {
            singleTarget.applyStatus(effect);
        }
        return false;
        //TODO
    }
    public boolean revert() {
        return false;
        //TODO
    }
}
