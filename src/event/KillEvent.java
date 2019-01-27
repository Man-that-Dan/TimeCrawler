package event;

import entity.Mob;

public class KillEvent extends Event{
    Mob killed;
    Mob damageSource;
    public KillEvent(Mob killed, Mob damageSource) {
        super();
        this.killed = killed;
        this.damageSource = damageSource;
    }

    public boolean execute() {
        killed.room.mobs.remove(killed);
        return true;
    }
    public boolean revert() {
        killed.room.mobs.add(killed);
        return true;
    }
}
