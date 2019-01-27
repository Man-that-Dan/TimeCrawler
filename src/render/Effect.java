package render;


import org.locationtech.jts.geom.Polygon;

import java.util.HashSet;

public class Effect {
    public static HashSet<Effect> effects = new HashSet<>();
    public Polygon poly;
    public Color color;
    public int ticks_remaining;

    public Effect(Polygon polygon, Color color, int duration) {
        this.poly = polygon;
        this.color = color;
        ticks_remaining = duration;
        effects.add(this);
    }

    public void tick() {
        ticks_remaining -= 1;
        if(ticks_remaining == 0) {
            effects.remove(this);
        }
        color.a = color.a / 2;
    }
}
