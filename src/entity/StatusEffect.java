package entity;

import event.StatusEvent;

public abstract class StatusEffect {
    String type;
    int expiration_timer;

    public StatusEffect(String t, int ticks){
      type = t;
      expiration_timer = ticks;
    };
    public StatusEffect(StatusEffect copy) {
        this.type = copy.type;
        this.expiration_timer = copy.expiration_timer;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof StatusEffect) {
            StatusEffect other = (StatusEffect) o;
            return this.type.equals(other.type);
        }
        return false;
    }

    public abstract void applyToMob(Mob m);
    public abstract void removeFromMob(Mob m);
}
