package world;

import java.util.Random;

public class Rand {
    public static long seed;
    public static Random world_rand = new Random(seed);
    public static Random room_rand = new Random(world_rand.nextInt());

    public static int world_next_int(int lowbound_inclusive, int highbound_exclusive) {
        return lowbound_inclusive + (int) (world_rand.nextDouble() * highbound_exclusive);
    }
    public static double room_next_double() {
        return room_rand.nextDouble();
    }
}