package event;

import java.util.PriorityQueue;

public abstract class Event implements Comparable<Event> {
    public static PriorityQueue<Event> event_queue = new PriorityQueue<>();
    public int priority;
    public abstract boolean execute();
    public abstract boolean revert();

    public Event() {
        event_queue.add(this);
    }

    @Override
    public int compareTo(Event e) {
        return this.priority - e.priority;
    }
}
