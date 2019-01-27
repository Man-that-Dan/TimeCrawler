
package event;

import entity.*;
import org.locationtech.jts.geom.Polygon;

public class StatusEvent extends Event {
    Entity entity;
    Polygon areaOfEffect;
    status_effect effect;

    public StatusEvent(Polygon areaOfEffect, status_effect s) {
        super();
        //TODO assign this to player character
        this.entity = player;
        this.areaOfEffect = areaOfEffect;
        this.effect = s;
    }

    public boolean execute() {

    }
    public boolean revert() {

    }
}
