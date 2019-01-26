package world;

import entity.Entity;

import java.util.HashSet;

public class Level {
    static int seed;
    static double scaleDifficulty(double previousDifficulty) {

    }
    double width;
    double height;


    HashSet<Terrain> terrain;
    HashSet<Entity> mobs;
    Level previous;
    Level next;

    double difficulty;

    public Level(Level previous) {
        difficulty = scaleDifficulty(previous.difficulty);
        width = difficulty * width;
    }
}
