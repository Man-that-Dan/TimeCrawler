package event;

import entity.Mob;

public class MoveEvent extends Event {
    public Mob mob;
    public double moved_x;
    public double moved_y;
    public MoveEvent(Mob e, double dx, double dy) {
        super();
        mob = e;
        moved_x = dx;
        moved_y = dy;
        this.generator = e;
    }
    public boolean execute() {
        mob.transpose(moved_x, moved_y);
        if(mob.getTerrainCollisions().size() == 0) {
            return true;
        } else {
            mob.transpose(-moved_x, -moved_y);
            moved_x = 0;
            moved_y = 0;
            return false;
        }
    }
    public boolean revert() {
        mob.transpose(-moved_x, -moved_y);
        return true;
    }
}
