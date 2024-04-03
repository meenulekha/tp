package seedu.address.model.event;

public class EventFactory {
    public static Event createEvent(EventName name, EventDate date, EventCategory category) {
        return new Event(name, date, category );
    }
}
