package gameEngine;

import java.util.ArrayList;

import entity.Entity;
import entity.Mob;
import processing.core.PApplet;
import world.Room;
import world.Terrain;

public class MainGameLoop extends PApplet {
    private static Room room;
    private static Mob player;

    /**
     * dispatch events has the samller or same timestamp to handler
     */
    public void dispatchEvents() {

    }

    /**
     * handling event
     *
     */
    public void eventHandler() {

    }
    /**
     *
     */
    public void settings() {
        size(800,800);

    }

    public void setup() {
        smooth();
        noStroke();
    }
    //rendering
    public void draw() {
        background(255);
        eventHandler();
        dispatchEvents();
        for(Entity go : room.mobs) {
//            go.move();
            fill(160,20,20);
            float[] info = go.getRenderInformation();
            fill(go.poly.color.r, go.poly.color.g, go.poly.color.b);
            beginShape();
            for(int i = 0; i < info.length; i += 2) {
                vertex(info[i], info[i+1]);
            }
            endShape();
        }
    }

    public void getUserInput() {
        if (keyCode == LEFT) {
            //move left
            player.forceMovement(-5, 0);
        }
        if (keyCode == RIGHT) {
            mobs.get(0).changeXSpeed(+5);
            //move right
        }
        if (keyCode == UP) {
            mobs.get(0).changeYSpeed(-5);
            //move up
        }
        if (keyCode == DOWN) {
            mobs.get(0).changeYSpeed(+5);
            //move down
        }
        keyCode = 0;
    }

    public static void main(String args[]){
        Entity player =
        player.setlocation(0, 0);
        mobs.add(player);
        PApplet.main("csc481_project.maingameLoop");
    }

}
