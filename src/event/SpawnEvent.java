package event;

import entity.Mob;

public class SpawnEvent extends Event {
    Mob spawning;
    public SpawnEvent(Mob spawning) {
        super();
        this.spawning = spawning;
    }

    public boolean execute() {
        spawning.room.mobs.add(spawning);
        return true;
    }

    public boolean revert() {
        spawning.room.mobs.remove(spawning);
        return true;
    }
}
