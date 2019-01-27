package entity;

import event.AttackEvent;
import event.Event;
import geometry.RectangleFactory;
import org.locationtech.jts.algorithm.Centroid;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.Envelope;

public class Player extends Mob {
    //the player's currently afflicted statuses
    status_effect[] statuses = new status_effect[5];
    double storedMovementX;
    double storedMovementY;
    RectangleFactory rf = new RectangleFactory();


    public Player(int x, int y){
        this.poly = rf.createRectangle(x, y, 10, 10);
        this.health = 100;
        this.attack = 20;
        this.speed = 5;
        this.direction = 1;
    }

    //long range vertical attack
    public void attack(){
        Event attacked;
        Polygon AoE;
        Coordinate playerCenter = Centroid.getCentroid(this.poly);
        switch(direction) {
            //east attack
            case 0: AoE = rf.createRectangle((playerCenter.x + 30), playerCenter.y, (this.poly.getEnvelopeInternal().getWidth() + 30), (2));
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //north attack
            case 1: AoE = rf.createRectangle((playerCenter.x), (playerCenter.y + 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //west attack
            case 2: AoE = rf.createRectangle((playerCenter.x - 30), playerCenter.y, (this.poly.getEnvelopeInternal().getWidth() + 30), (2));
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //south attack
            case 3: AoE = rf.createRectangle((playerCenter.x), (playerCenter.y - 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
        };
    };

    public void storeMovement(double dx, double dy) {
        storedMovementX += dx;
        storedMovementY += dy;
    }
}
