package event;

import java.util.PriorityQueue;

public abstract class Event implements Comparable<Event> {
    public static PriorityQueue<Event> event_queue = new PriorityQueue<>();
    public double priority;
    public abstract boolean execute();
    public abstract boolean revert();

    public Event() {
        event_queue.add(this);
    }

    @Override
    public int compareTo(Event e) {
        if(this.priority > e.priority) {
            return 1;
        } else if(this.priority < e.priority) {
            return -1;
        } else return 0;
    }
}
