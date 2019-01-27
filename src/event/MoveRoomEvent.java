package event;

import entity.Mob;
import entity.Player;
import world.Room;

import java.security.PublicKey;

public class MoveRoomEvent extends Event {
    Mob mob;
    public Room newRoom;
    public Room oldRoom;
    double newX;
    double newY;
    double oldX;
    double oldY;
    public MoveRoomEvent(Mob e, Room newRoom, double newX, double newY) {
        super();
        mob = e;
        oldX = mob.x;
        oldY = mob.y;
        this.generator = e;
        oldRoom = e.room;
        this.newRoom = newRoom;
        this.priority = 0.9999; //THIS SHOULD HAPPEN LAST
    }

    public boolean execute() {
        if(mob instanceof Player) {
            oldRoom.mobs.remove(mob);
            mob.room = newRoom;
            mob.room.mobs.add(mob);
            mob.transpose(-mob.x, -mob.y);
            mob.transpose(newX, newY);
            return true;
        } else {
            return false;
        }
    }

    public boolean revert() {
        if(mob instanceof Player) {
            mob.room.mobs.remove(mob);
            mob.room = oldRoom;
            mob.room.mobs.add(mob);
            mob.transpose(-mob.x, -mob.y);
            mob.transpose(oldX, oldY);
        }
        return true;
    }
}
