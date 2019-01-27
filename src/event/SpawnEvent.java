package event;

import entity.Mob;

public class SpawnEvent extends Event {
    Mob spawning;
    public SpawnEvent(Mob spawning) {
        super();
        this.spawning = spawning;
        System.out.println("Spawn event created");
    }

    public boolean execute() {
        spawning.room.mobs.add(spawning);
        if(spawning.getTerrainCollisions().size() != 0) {
            spawning.room.mobs.remove(spawning);
            spawning.spawned = false;
            spawning = null;
            return false;
        }
        spawning.spawned = true;
        return true;
    }

    public boolean revert() {
        if(spawning == null) {
            return false;
        } else {
            spawning.room.mobs.remove(spawning);
            spawning.spawned = false;
            return true;
        }
    }
}
