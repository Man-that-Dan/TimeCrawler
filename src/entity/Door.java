package entity;

import geometry.RectangleFactory;
import render.Color;
import world.Room;

public class Door extends Entity {
    public static final double WIDTH = 40;
    public Room nextRoom;
    double tpx;
    double tpy;
    int moveDirection;

    public Door(double x, double y, Room room, Room nextRoom, int moveDirection) {
        super(x, y, room);
        RectangleFactory rf = new RectangleFactory();
        this.poly = rf.createRectangle(x - WIDTH / 2, y - WIDTH / 2, WIDTH, WIDTH);
        this.nextRoom = nextRoom;
        this.color = new Color(127,255,200);
        this.moveDirection = moveDirection;

    }
}
