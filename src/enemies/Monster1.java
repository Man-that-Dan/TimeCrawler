package enemies;

import entity.Entity;
import event.AttackEvent;
import event.Event;
import geometry.Rectangle;

public class Monster1 extends Enemy {


    int damage = 5;
    int speed = 5;

    public Monster1(int x, int y, double diff){

        this.poly.x = x;
        this.poly.y = y;
        this.poly.width = 5;
        this.poly.height = 5;
        difficulty = diff;
    }

    //long range vertical attack
    public void attack(){
        Rectangle AoE = new Rectangle(this.poly.x, this.poly.y, (this.poly.x + 70), (2)  );
        Event attacked = new AttackEvent(this, AoE, attack * difficulty);
    };
}
