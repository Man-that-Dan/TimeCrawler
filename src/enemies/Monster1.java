package enemies;

import entity.Entity;
import geometry.Rectangle;

public class Monster1 extends Enemy {


    int damage = 5;
    int speed = 5;

    public Monster1(int x, int y, double diff){
        this.posx = x;
        this.posy = y;
        this.poly.x = x;
        this.poly.y = y;
        this.poly.width = 5;
        this.poly.height = 5;
        difficulty = diff;
    }
}
