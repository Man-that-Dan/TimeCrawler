package entity;

import event.*;
import geometry.RectangleFactory;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.util.AffineTransformation;
import world.Rand;
import world.Room;

public class Player extends Mob {
    //the player's currently afflicted statuses
    StatusEffect[] statuses = new StatusEffect[5];
    double storedMovementX;
    double storedMovementY;
    RectangleFactory rf = new RectangleFactory();


    public Player(double x, double y, Room room){
        super(x, y, room);
        this.poly = rf.createRectangle(x - 5, y - 5, 10, 10);
        this.health = 10000000;
        this.attack = 2000;
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
            case 0: AoE = rf.createRectangle(playerCenter.x, playerCenter.y - 1, this.poly.getEnvelopeInternal().getWidth() + 30, 2);
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //north attack
            case 1: AoE = rf.createRectangle(playerCenter.x - 1, playerCenter.y, 2, this.poly.getEnvelopeInternal().getHeight() + 30);
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //west attack
            case 2: AoE = rf.createRectangle(playerCenter.x, playerCenter.y - 1, -this.poly.getEnvelopeInternal().getWidth() - 30, 2);
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
             //south attack
            case 3: AoE = rf.createRectangle(playerCenter.x - 1, playerCenter.y, 2, -this.poly.getEnvelopeInternal().getHeight() - 30);
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
            //north east attack
            case 4: AoE = rf.createRectangle((playerCenter.x + 30), (playerCenter.y + 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                    AffineTransformation rotright = new AffineTransformation();
                    rotright.rotate(-45.0);
                    AoE = new GeometryFactory().createPolygon((rotright.transform(AoE)).getCoordinates());
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
            //north west attack
            case 5: AoE = rf.createRectangle((playerCenter.x - 30), (playerCenter.y + 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                    AffineTransformation rotleft = new AffineTransformation();
                    rotleft.rotate(45.0);
                    AoE = new GeometryFactory().createPolygon((rotleft.transform(AoE)).getCoordinates());
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
            //south west attack
            case 6: AoE = rf.createRectangle((playerCenter.x - 30), (playerCenter.y - 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                    AffineTransformation rotlefter = new AffineTransformation();
                    rotlefter.rotate(135.0);
                    AoE = new GeometryFactory().createPolygon((rotlefter.transform(AoE)).getCoordinates());
                    attacked = new AttackEvent(this, AoE, attack);
                    break;
            //south east attack
            case 7: AoE = rf.createRectangle((playerCenter.x + 30), (playerCenter.y - 30), (2), (this.poly.getEnvelopeInternal().getHeight() + 30));
                    AffineTransformation rotrighter = new AffineTransformation();
                    rotrighter.rotate(-135.0);
                    AoE = new GeometryFactory().createPolygon((rotrighter.transform(AoE)).getCoordinates());
                    attacked = new AttackEvent(this, AoE, attack);
                    break;

        };
    };

    public void storeMovement(double dx, double dy) {
        storedMovementX += dx;
        storedMovementY += dy;
    }

    boolean willAttack = false;
    public void doAttack() {
        willAttack = true;
    }

    public void updateAI() {
        if(storedMovementX == 0 && storedMovementY == 0)  {

        } else {
            double scale = speed / Math.sqrt((storedMovementX * storedMovementX + storedMovementY * storedMovementY));
            new MoveEvent(this, storedMovementX * scale, storedMovementY * scale);
            storedMovementX = 0;
            storedMovementY = 0;
        }
        if(willAttack) {
            attack();
            willAttack = false;
        }
        if(!spawned) {
            double newx = Rand.mob_next_double(0, room.width);
            double newy = Rand.mob_next_double(0, room.height);
            this.x = newx;
            this.y = newy;
            this.poly = rf.createRectangle(x - 5, y - 5, 10, 10);
            new SpawnEvent(this);
        }
    }

    @Override
    public void respond(Event e) {
        if(e instanceof KillEvent) {
            //Game over
            new EndGameEvent();
        }
    }
}
