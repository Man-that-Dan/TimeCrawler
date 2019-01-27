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
        System.out.println("Spawn event executed");
        return true;
    }

    public boolean revert() {
        spawning.room.mobs.remove(spawning);
        return true;
    }
}
