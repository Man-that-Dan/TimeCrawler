package world;

import java.util.Random;

public class Rand {
    public static long seed;
    public static Random world_rand = new Random(seed);

    public static int world_next_int(int lowbound_inclusive, int highbound_exclusive) {
        return lowbound_inclusive + (int) (world_rand.nextDouble() * highbound_exclusive);
    }
}