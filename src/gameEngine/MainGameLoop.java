package csc481_project;

import entity.Entity;
import entity.Mob;
import processing.core.PApplet;
import world.Room;

public class MainGameLoop extends PApplet {
    private static Room room;
    private static Mob player;
    private static long tick = 0;
    //time increment of time through one loop through
    private static int timeIncrement = 5000;
    private static long deltaTime;
    private static long beginTime;

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
            //move right
            player.forceMovement(+5, 0);
        }
        if (keyCode == UP) {
            //move up
            player.forceMovement(0, +5);
        }
        if (keyCode == DOWN) {
            //move down
            player.forceMovement(0, -5);
        }
        keyCode = 0;
        deltaTime +=  System.currentTimeMillis() - beginTime;
        beginTime = System.currentTimeMillis();
        if(timeIncrement < deltaTime ) {
            tick++;
            deltaTime -= timeIncrement;
            if(tick%5 == 0) {
                System.out.println(tick);
            }
        }

    }

    public static void main(String args[]){
//        player.setlocation(0, 0);
        room.mobs.add(player);
        //grab current time
        beginTime = System.currentTimeMillis();
        PApplet.main("csc481_project.maingameLoop");
    }

}
