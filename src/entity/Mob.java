package entity;


import event.MoveEvent;

public abstract class Mob extends Entity {
    public double attack;
    public double health;
    public int speed;

    public void forceMovement(double dx, double dy) {
        MoveEvent me = new MoveEvent(this, dx, dy);
    }
}
