package gameEngine;

import enemies.BigMonster;
import maps.Locator;
import entity.Entity;
import entity.Mob;
import entity.Player;
import event.Event;
import event.SpawnEvent;
import org.locationtech.jts.geom.Coordinate;
import processing.core.PApplet;
import world.Rand;
import world.Room;

import java.util.Random;

public class MainGameLoop extends PApplet {
    public static final double HUD_WIDTH = 50;
    private static Room room;
    private static Player player;
    private static long tick = 0;
    //time increment of time through one loop through
    private static int timeIncrement = 50;
    private static long deltaTime;
    private static long beginTime;
    public static double longitude;

    /**
     * dispatch events has the samller or same timestamp to handler
     */
    public void dispatchEvents() {
        for(Mob m : room.mobs) {
            m.updateAI();
        }
        if(tick % 2 == 0) {

        }
        if (tick % 3 == 0) {

        }
        if (tick % 4 == 0) {

        }
        if (tick % 100 == 0) {
            for(int i = 0; i < 3; i++) {
                new SpawnEvent(new BigMonster(Rand.mob_next_double(0, room.width), Rand.mob_next_double(0, room.height), room, 1.0));
            }
        }
    }

    /**
     * handling event
     *
     */ public void eventHandler() {
        deltaTime +=  System.currentTimeMillis() - beginTime;
        beginTime = System.currentTimeMillis();
        if(timeIncrement < deltaTime ) {
            //We should go on to the next tick
            deltaTime -= timeIncrement;
            if(tick%5 == 0) {
                System.out.println("Tick number: " + tick);
            }
            dispatchEvents();
            //Do all the queued events
            boolean isDone = false;
            while(!Event.event_queue.isEmpty() && !isDone) {
                Event next = Event.event_queue.peek();
                if(next.priority < 1.0) {
                    if(!Event.event_queue.poll().execute()) {
                        next.generator.respond(next);
                    }
                } else {
                    isDone = true;
                }
            }
            tick++;
        }
    }
    /**
     *
     */
    public void settings() {
        size((int) (Room.width + HUD_WIDTH),(int) (Room.height + HUD_WIDTH));
    }

    public void setup() {
        smooth();
        noStroke();
    }
    //rendering
    public void draw() {
        background(255);
        getUserInput();
        eventHandler();
        for(Entity go : room.mobs) {
            Coordinate[] info = go.getRenderInformation();
            fill(go.color.r, go.color.g, go.color.b);
            beginShape();
            for(int i = 0; i < info.length; i++) {
                vertex((float) (info[i].x + HUD_WIDTH), (float) (info[i].y + HUD_WIDTH));
            }
            endShape();
        }
        for(Entity go : room.terrain) {
            Coordinate[] info = go.getRenderInformation();
            fill(go.color.r, go.color.g, go.color.b);
            beginShape();
            for(int i = 0; i < info.length; i++) {
                vertex((float) (info[i].x + HUD_WIDTH), (float) (info[i].y + HUD_WIDTH));
            }
            endShape();

        }
        eventHandler();
    }

    public void getUserInput() {
        if (keyCode == LEFT) {
            //move left
            player.storeMovement(-5, 0);
        }
        if (keyCode == RIGHT) {
            //move right
            player.storeMovement(+5, 0);
        }
        if (keyCode == UP) {
            //move up
            player.storeMovement(0, +5);
        }
        if (keyCode == DOWN) {
            //move down
            player.storeMovement(0, -5);
        }
        keyCode = 0;
    }

    public static void main(String args[]){
        Rand.init((new Random()).nextLong());
        room = new Room(0,0);
        room.init();
        Locator location = new Locator();
//        longitude = location.getLong();
        longitude = 0.5;
//        player.setlocation(0, 0);
//        room.mobs.add(player);
        player = new Player(400, 400, room);
        new SpawnEvent(player);
        //grab current time
        beginTime = System.currentTimeMillis();
        PApplet.main("gameEngine.MainGameLoop");
    }

}
