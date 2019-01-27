package gameEngine;

import java.util.ArrayList;

import entity.Entity;
import processing.core.PApplet;
public class MainGameLoop extends PApplet {
    private static ArrayList<Entity>gameObjects = new ArrayList<Entity>(5);

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
        for(Entity go : gameObjects) {
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
            gameObjects.get(0).;
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
    }

    public static void main(String args[]){
        Entity player =
        player.setlocation(0, 0);
        gameObjects.add(player);
        PApplet.main("csc481_project.maingameLoop");
    }

}
