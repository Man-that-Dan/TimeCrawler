package entity;

public class FrostStatus extends StatusEffect {
    public FrostStatus(int duration) {
        super("frost", duration);
    }
    public void applyToMob(Mob mob) {
        mob.speed *= 0.5;
    }
    public void removeFromMob(Mob mob) {
        mob.speed *= 2;
    }
}
