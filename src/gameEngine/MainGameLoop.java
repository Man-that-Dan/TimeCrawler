package csc481_project;

import java.util.ArrayList;

import object.GameObject;
import object.Squares;
import processing.core.PApplet;
public class maingameLoop extends PApplet {
    private static long tick = 0;
    //time increment of time through one loop through
    private static int timeIncrement = 5000;
    private static long deltaTime;
    private static long beginTime;
    private static ArrayList<GameObject>gameObjects = new ArrayList<GameObject>(5);

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
    /**
     * detect collision
     */
    public void collision(GameObject goA, GameObject goB) {

    }
    //rendering
    public void draw() {
        background(255);
        eventHandler();
        dispatchEvents();
        for(GameObject go : gameObjects) {
            go.move();
            fill(160,20,20);
            int[] info = go.getRenderInformation();
            rect(info[0],info[1],info[2],info[3]);
        }
        if (keyCode == LEFT) {
            //move left
            gameObjects.get(0).changeXSpeed(-5);
        }
        if (keyCode == RIGHT) {
            gameObjects.get(0).changeXSpeed(+5);
            //move right
        }
        if (keyCode == UP) {
            gameObjects.get(0).changeYSpeed(-5);
            //move up
        }
        if (keyCode == DOWN) {
            gameObjects.get(0).changeYSpeed(+5);
            //move down
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
        GameObject player = new Squares(50, 50);
        player.setlocation(0, 0);
        gameObjects.add(player);
        //grab current time
        beginTime = System.currentTimeMillis();
        PApplet.main("csc481_project.maingameLoop");
    }

}
