package entity;

import event.AttackEvent;
import event.Event;
import geometry.RectangleFactory;
import org.locationtech.jts.algorithm.Centroid;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

public class Player extends Mob {
    //the player's currently afflicted statuses
    status_effect[] statuses = new status_effect[5];
    double storedMovementX;
    double storedMovementY;
    RectangleFactory rf = new RectangleFactory();


    public Player(int x, int y){
        this.poly.x = x;
        this.poly.y = y;
        this.health = 100;
        this.attack = 20;
        this.speed = 5;
        this.direction = 1;
    }

    //long range vertical attack
    public void attack(){
        Event attacked;
        Coordinate playerCenter = Centroid.getCentroid(this.poly);
        switch(direction) {
            //east attack
            case 0: Polygon AoE = rf.createRectangle((playerCenter.x + 30), playerCenter.y, (playerCenter.y + 30), (2));
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //north attack
            case 1: Polygon AoE = new Rectangle((this.poly.x), (this.poly.y + 30), (2), (this.poly.y + 30));
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //west attack
            case 2: Polygon AoE = new Rectangle((this.poly.x - 30), this.poly.y, (this.poly.x + 30), (2));
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //south attack
            case 3: Polygon AoE = new Rectangle((this.poly.x), (this.poly.y - 30), (2), (this.poly.y + 30));
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
        };
    };

    public void storeMovement(double dx, double dy) {
        storedMovementX += dx;
        storedMovementY += dy;
    }
}
