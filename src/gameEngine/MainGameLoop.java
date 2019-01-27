package gameEngine;

import enemies.BigMonster;
import enemies.Freezieboi;
import entity.StatusEffect;
import event.*;
import maps.Locator;
import entity.Entity;
import entity.Mob;
import entity.Player;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import render.Effect;
import world.Rand;
import world.Room;

import java.sql.Time;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class MainGameLoop extends PApplet {
    public static final double HUD_WIDTH = 50;
    private static Room room;
    public static Player player;
    private static long tick = 0;
    //time increment of time through one loop through
    private static int timeIncrement = 50;
    private static long deltaTime;
    private static long beginTime;
    public static double longitude;
    public static boolean reverse = false;
//    public static HashSet<Effect> effects = new HashSet<>();

    /**
     * dispatch events has the samller or same timestamp to handler
     */
    public void dispatchEvents() {
        for(Mob m : room.mobs) {
            m.tickStatuses();
            m.updateAI();
        }
        if(tick % 2 == 0) {

        }
        if (tick % 3 == 0) {

        }
        if (tick % 4 == 0) {

        }
        if (tick % 100000000 == 0) {
            for(int i = 0; i < 1; i++) {
                new SpawnEvent(new BigMonster(Rand.mob_next_double(0, room.width), Rand.mob_next_double(0, room.height), room, 1.0));
            }
            for(int i = 0; i < 1; i++) {
                new SpawnEvent(new Freezieboi(Rand.mob_next_double(0, room.width), Rand.mob_next_double(0, room.height), room, 1.0));
            }
        }
    }

    /**
     * handling event
     *
     */
    public void eventHandler() {
        deltaTime +=  System.currentTimeMillis() - beginTime;
        beginTime = System.currentTimeMillis();
        if(timeIncrement < deltaTime ) {
            //We should go on to the next tick
            deltaTime -= timeIncrement;
            dispatchEvents();
            //Do all the queued events
            boolean isDone = false;
            LinkedList<Event> executionSequence = new LinkedList<>();
            while(!Event.event_queue.isEmpty() && !isDone) {
                Event next = Event.event_queue.peek();
                if(next.priority < 1.0) {
                    Event.event_queue.poll().execute();
                    executionSequence.addFirst(next);
                    next.generator.respond(next);
                } else {
                    isDone = true;
                }
            }
            room = player.room;
            TimeLine.addTick(executionSequence);
            tick++;
        }
        if(reverse) {
            for(int i = 0; i < 2; i++) {
                if (!TimeLine.previousEvents.isEmpty()) {
                    LinkedList<Event> pe = TimeLine.previousEvents.poll();
                    for (Event e : pe) {
                        e.revert();
                    }
                }
                room = player.room;
            }
            reverse = false;
        }
        if(room.mobs.size() == 1) {
            //Room has been defeated
            System.out.println("Room defeated");
            room.generateDoors();
        }
    }
    PFont font;
    /**
     *
     */
    public void settings() {
        size((int) (Room.width + 2 * HUD_WIDTH),(int) (Room.height + 2 * HUD_WIDTH));
    }
//    PImage img;

    public void setup() {
        Locator location = new Locator();
        longitude = 0.5;
        smooth();
        noStroke();
        font = createFont("Arial",16,true); // STEP 2 Create Font
//        img = loadImage("Assets/pixil-frame-0.png");
    }
    //rendering
    public void draw() {
        if(player.health > 0) {
            PImage bg;
            if(longitude > 39.00) {
                bg = loadImage("Assets/Snowfloor.jpeg");
            } else {
                bg = loadImage("Assets/Regularfloor.jpg");
            }
            background(bg);
            getUserInput();
            eventHandler();
            for (Entity go : room.mobs) {
                Coordinate[] info = go.getRenderInformation();
                fill(go.color.r, go.color.g, go.color.b);
                beginShape();
                for (int i = 0; i < info.length; i++) {
                    vertex((float) (info[i].x + HUD_WIDTH), (float) (info[i].y + HUD_WIDTH));
                }
                endShape();
//                img.resize(50, 50);
//                image(img, (float) go.x + img.width / 2, (float) go.y + img.height / 2);
            }
            for (Entity go : room.terrain) {
                Coordinate[] info = go.getRenderInformation();
                fill(go.color.r, go.color.g, go.color.b);
                beginShape();
                for (int i = 0; i < info.length; i++) {
                    vertex((float) (info[i].x + HUD_WIDTH), (float) (info[i].y + HUD_WIDTH));
                }
                endShape();
            }
            for (Entity go : room.doors) {
                Coordinate[] info = go.getRenderInformation();
                fill(go.color.r, go.color.g, go.color.b);
                beginShape();
                for (int i = 0; i < info.length; i++) {
                    vertex((float) (info[i].x + HUD_WIDTH), (float) (info[i].y + HUD_WIDTH));
                }
                endShape();
            }
            for (Effect effect : Effect.effects) {
                Coordinate[] info = effect.poly.getCoordinates();
                fill(effect.color.r, effect.color.g, effect.color.b);
                tint(255, effect.color.a);
                beginShape();
                for (int i = 0; i < info.length; i++) {
                    vertex((float) (info[i].x + HUD_WIDTH), (float) (info[i].y + HUD_WIDTH));
                }
                endShape();
                effect.tick();
            }
            Effect.effects.clear();
            //HUD

            textFont(font, 16);
            fill(0);
            String hudString = player.health + "";
            for(StatusEffect se : player.statusEffects) {
                hudString += " " + se.type + ": " + se.expiration_timer;
            }
            text(hudString, 20, 20);

            ///HUD

            eventHandler();
        } else {
            background(155, 0, 0);
            textFont(font, 80);
            fill(0);
            text("GAME OVER", 0, 400 + (int) HUD_WIDTH - 40);

        }
    }

    public void getUserInput() {
        if (keyCode == LEFT) {
            //move left
            player.storeMovement(-5, 0);
            player.direction = 2;
        }
        if (keyCode == RIGHT) {
            //move right
            player.storeMovement(+5, 0);
            player.direction = 0;
        }
        if (keyCode == DOWN) {
            //move up
            player.storeMovement(0, +5);
            player.direction = 1;
        }
        if (keyCode == UP) {
            //move down
            player.storeMovement(0, -5);
            player.direction = 3;
        }
        if (key == ' '){
            player.doAttack();
//            System.out.println("good");
        }
        if(key == 'z') {
            reverse = true;
        }
        keyCode = 0;
        key = '\0';
    }

    public static void main(String args[]){
        Rand.init((new Random()).nextLong());
        room = new Room(0,0);
        room.init();

//        player.setlocation(0, 0);
//        room.mobs.add(player);
        player = new Player(400, 400, room);
        new SpawnEvent(player);
        //grab current time
        beginTime = System.currentTimeMillis();
        PApplet.main("gameEngine.MainGameLoop");
    }

}
