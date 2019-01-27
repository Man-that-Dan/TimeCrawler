package enemies;

import entity.Entity;
import geometry.Rectangle;

public class BigMonster extends Enemy {


    int damage = 15;
    int speed = 1;
    public BigMonster(int x, int y, double diff){
        this.posx = x;
        this.posy = y;
        this.poly.x = x;
        this.poly.y = y;
        this.poly.width = 10;
        this.poly.height = 10;
        difficulty = diff;
    }


}
