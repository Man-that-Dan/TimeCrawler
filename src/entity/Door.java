package entity;

import geometry.RectangleFactory;
import render.Color;
import world.Room;

public class Door extends Entity {
    public static final double WIDTH = 40;
    public Room nextRoom;
    public double tpx;
    public double tpy;
    int moveDirection;

    public Door(double x, double y, Room room, Room nextRoom, int moveDirection) {
        super(x, y, room);
        RectangleFactory rf = new RectangleFactory();
        this.poly = rf.createRectangle(x - WIDTH / 2, y - WIDTH / 2, WIDTH, WIDTH);
        this.nextRoom = nextRoom;
        this.color = new Color(127,255,200);
        this.moveDirection = moveDirection;
        switch(moveDirection) {
            case 0:
                tpx = 30;
                tpy = 400;
                break;
            case 1:
                tpx = 400;
                tpy = 30;
                break;
            case 2:
                tpx = 800-30;
                tpy = 400;
                break;
            case 3:
                tpx = 400;
                tpy = 800-30;
        }
    }
}
