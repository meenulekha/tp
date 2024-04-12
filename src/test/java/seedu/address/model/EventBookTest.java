package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventCategory;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;



public class EventBookTest {

    @Test
    public void constructor_noArgs_success() {
        EventBook eventBook = new EventBook();
        assertNotNull(eventBook);
    }

    @Test
    public void constructor_withReadOnlyEventBook_success() {
        EventBook eventBook = new EventBook(new ReadOnlyEventBook() {
            @Override
            public ObservableList<Event> getEventList() {
                return FXCollections.observableArrayList(Collections.emptyList());
            }
        });
        assertNotNull(eventBook);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        EventBook eventBook = new EventBook();
        assertThrows(NullPointerException.class, () -> eventBook.setEvents(null));
    }

    @Test
    public void resetData_nullData_throwsNullPointerException() {
        EventBook eventBook = new EventBook();
        assertThrows(NullPointerException.class, () -> eventBook.resetData(null));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        EventBook eventBook = new EventBook();
        assertThrows(NullPointerException.class, () -> eventBook.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInList_returnsFalse() {
        EventBook eventBook = new EventBook();
        Event event = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        assertFalse(eventBook.hasEvent(event));
    }

    @Test
    public void addEvent_nullEvent_throwsNullPointerException() {
        EventBook eventBook = new EventBook();
        assertThrows(NullPointerException.class, () -> eventBook.addEvent(null));
    }

    @Test
    public void removeEvent_nullEvent_throwsNullPointerException() {
        EventBook eventBook = new EventBook();
        assertThrows(NullPointerException.class, () -> eventBook.removeEvent(null));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        EventBook eventBook = new EventBook();
        assertTrue(eventBook.equals(eventBook));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        EventBook eventBook = new EventBook();
        assertFalse(eventBook.equals(new Object()));
    }

    @Test
    public void equals_differentEvents_returnsFalse() {
        EventBook eventBook1 = new EventBook();
        EventBook eventBook2 = new EventBook();
        Event event1 = new Event(new EventName("Meeting1"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        Event event2 = new Event(new EventName("Meeting2"), new EventDate("02-01-2025"), new EventCategory("STAFF"));
        eventBook1.addEvent(event1);
        eventBook2.addEvent(event2);
        assertFalse(eventBook1.equals(eventBook2));
    }

    @Test
    public void hashCode_sameObject_equalHashCodes() {
        EventBook eventBook = new EventBook();
        assertEquals(eventBook.hashCode(), eventBook.hashCode());
    }


}
