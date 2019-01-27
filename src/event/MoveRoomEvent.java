package event;

import entity.Mob;
import entity.Player;
import world.Room;

public class MoveRoomEvent extends Event {
    Mob mob;
    Room newRoom;
    Room oldRoom;
    public MoveRoomEvent(Mob e, Room newRoom) {
        super();
        mob = e;
        this.generator = e;
        oldRoom = e.room;
        this.newRoom = newRoom;
    }

    public boolean execute() {
        if(mob instanceof Player) {
            mob.room = newRoom;
            mob.transpose(-mob.x, -mob.y);
            mob.transpose()
        } else {

        }
    }

    public boolean revert() {

    }
}
