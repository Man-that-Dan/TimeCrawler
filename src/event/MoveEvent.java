package event;

import entity.Entity;
import event.Event;

public class MoveEvent extends Event {
    Entity entity;
    double moved_x;
    double moved_y;
    public MoveEvent(Entity e, double dx, double dy) {
        super();
        entity = e;
        moved_x = dx;
        moved_y = dy;
    }
    public boolean execute() {
        entity.transpose(moved_x, moved_y);
        if(entity.getTerrainCollisions().size() == 0) {
            return true;
        } else {
            entity.transpose(-moved_x, -moved_y);
            moved_x = 0;
            moved_y = 0;
            return false;
        }
    }
    public boolean revert() {
        entity.transpose(-moved_x, -moved_y);
        return true;
    }
}
