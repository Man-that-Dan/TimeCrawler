package entity;

import event.AttackEvent;
import event.Event;
import event.MoveEvent;
import geometry.RectangleFactory;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

public class Player extends Mob {
    //the player's currently afflicted statuses
    StatusEffect[] statuses = new StatusEffect[5];
    double storedMovementX;
    double storedMovementY;
    RectangleFactory rf = new RectangleFactory();


    public Player(double x, double y){
        super(x, y);
        this.poly = rf.createRectangle(x - 5, y - 5, 10, 10);
        this.health = 100;
        this.attack = 20;
        this.speed = 5;
        this.direction = 1;
    }

    //long range vertical attack
    public void attack(){
        Event attacked;
        Polygon AoE;
//        Coordinate playerCenter = Centroid.getCentroid(this.poly);
        Coordinate playerCenter = new Coordinate(x, y);
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
            //north east attack
            case 4: AoE = rf.createRectangle((playerCenter.x + 30), (playerCenter.y + 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                attacked = new AttackEvent(this, AoE, attack);
                break;
            //north west attack
            case 5: AoE = rf.createRectangle((playerCenter.x - 30), (playerCenter.y + 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                attacked = new AttackEvent(this, AoE, attack);
                break;
            //south west attack
            case 6: AoE = rf.createRectangle((playerCenter.x - 30), (playerCenter.y - 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                attacked = new AttackEvent(this, AoE, attack);
                break;
            //south east attack
            case 7: AoE = rf.createRectangle((playerCenter.x + 30), (playerCenter.y - 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                attacked = new AttackEvent(this, AoE, attack);
                break;

        };
    };

    public void storeMovement(double dx, double dy) {
        storedMovementX += dx;
        storedMovementY += dy;
    }

    public void updateAI() {
        if(storedMovementX == 0 && storedMovementY == 0)  {

        } else {
            double scale = 1 / (storedMovementX * storedMovementX + storedMovementY * storedMovementY);
            new MoveEvent(this, storedMovementX * scale, storedMovementY * scale);
            storedMovementX = 0;
            storedMovementY = 0;
        }
    }
}
