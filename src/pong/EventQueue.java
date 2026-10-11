package pong;

import java.util.ArrayList;
import java.util.List;

public class EventQueue {
	public static List<Events> events = new ArrayList<>();

    public static void push(Events event) {
        events.add(event);
    }
}
