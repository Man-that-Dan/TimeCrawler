package enemies;

import event.*;

import entity.*;
import geometry.Rectangle;

//enemy base class - abstract class
public class Enemy extends mob {
    double difficulty;
    int damage = 5;
    int speed = 5;

    public void attack(){
        Rectangle AoE = new Rectangle(this.poly.x, this.poly.y, (this.poly.x + 5), (this.poly.y + 5)  );
        Event attacked = new AttackEvent(this, AoE, attack * difficulty);
    };

}