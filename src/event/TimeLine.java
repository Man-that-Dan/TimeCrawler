package event;

import java.util.LinkedList;

public class TimeLine {
    public static final int MAX_TICKS = 50;
    public static LinkedList<LinkedList<Event> > previousEvents = new LinkedList<>();

    public static void addTick(LinkedList<Event> events) {
        previousEvents.addFirst(events);
        if(previousEvents.size() > MAX_TICKS) {
            previousEvents.removeLast();
        }
    }

}
