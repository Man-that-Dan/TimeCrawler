package entity;

public class StatusEffect {
    String type;
    int expiration_timer;

    public StatusEffect(String t, int ticks){
      type = t;
      expiration_timer = ticks;
    };

    @Override
    public int hashCode() {
        return type.hashCode();
    }


}
