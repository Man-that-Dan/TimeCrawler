package enemies;

import entity.*;

import event.*;
import geometry.Rectangle;

public class Freezieboi extends Enemy {


    int damage = 5;
    int speed = 5;
    StatusEffect frostbolt = new StatusEffect("Freeze", 3);

    public Freezieboi(int x, int y, double diff){

        this.poly.x = x;
        this.poly.y = y;
        this.poly.width = 5;
        this.poly.height = 5;
        difficulty = diff;
    }

    //long range horizontal freezing attack
    public void attack(){
        Rectangle AoE = new Rectangle(this.poly.x, this.poly.y, (2), (this.poly.y + 70)  );
        Event attacked = new AttackEvent(this, AoE, attack * difficulty);
        Event frozen = new StatusEvent(AoE, new StatusEffect("Freeze", 3));
    };
}
