package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void constructor_validArguments_success() {
        EventName eventName = new EventName("Meeting");
        EventDate eventDate = new EventDate("01-01-2025");
        EventCategory eventCategory = new EventCategory("STAFF");
        Event event = new Event(eventName, eventDate, eventCategory);

        assertEquals(eventName, event.getEventName());
        assertEquals(eventDate, event.getEventDate());
        assertEquals(eventCategory, event.getEventCategory());
    }

    @Test
    public void isSameEvent_sameEvent_true() {
        EventName eventName = new EventName("Meeting");
        EventDate eventDate = new EventDate("01-01-2025");
        EventCategory eventCategory = new EventCategory("STAFF");
        Event event1 = new Event(eventName, eventDate, eventCategory);
        Event event2 = new Event(eventName, eventDate, eventCategory);

        assertTrue(event1.isSameEvent(event2));
    }

    @Test
    public void isSameEvent_differentEvents_false() {
        Event event1 = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        Event event2 = new Event(new EventName("Conference"), new EventDate("02-01-2025"), new EventCategory("STAFF"));

        assertFalse(event1.isSameEvent(event2));
    }

    @Test
    public void equals_differentEvents_false() {
        Event event1 = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        Event event2 = new Event(new EventName("Conference"), new EventDate("02-01-2025"), new EventCategory("STAFF"));

        assertFalse(event1.equals(event2));
    }

    @Test
    public void hashCode_sameEvents_equalHashCodes() {
        Event event1 = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        Event event2 = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));

        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    public void toString_validEvent_correctStringRepresentation() {
        Event event = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        String expectedString = "eventName=Meeting, eventDate=01-01-2025, eventCategory=STAFF";

        assertEquals(expectedString, event.toString());
    }
}

