package seedu.address.model.event;

import java.util.Objects;

/**
 * Represents a Event in the event book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {
    private final EventName eventName;
    private final EventCategory eventCategory;
    private final EventDate eventDate;

    /**
     * Constructs an Event object with the specified name, date, and category.
     *
     * @param eventName The name of the event.
     * @param eventDate The date of the event.
     * @param eventCategory The category of the event.
     */
    public Event(EventName eventName, EventDate eventDate, EventCategory eventCategory) {
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.eventDate = eventDate;
    }

    public EventName getEventName() {
        return this.eventName;
    }

    public EventCategory getEventCategory() {
        return this.eventCategory;
    }

    public EventDate getEventDate() {
        return this.eventDate;
    }

    /**
     * Returns true if both events have the same event name and event category.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && (otherEvent.getEventName().equals(getEventName())
                || otherEvent.getEventDate().equals(getEventDate())
                || otherEvent.getEventCategory().equals(getEventCategory()));
    }

    /**
     * Returns true if both events have the same event name, event date and event category.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent != null
                && (eventName.equals(otherEvent.eventName)
                && eventDate.equals(otherEvent.eventDate)
                && eventCategory.equals(otherEvent.eventCategory));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, eventDate, eventCategory);
    }

    @Override
    public String toString() {
        return "eventName="
                + eventName
                + ", eventDate="
                + eventDate
                + ", eventCategory="
                + eventCategory;
    }


}
