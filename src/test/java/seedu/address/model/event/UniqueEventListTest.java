package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
public class UniqueEventListTest {

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        assertFalse(uniqueEventList.contains(new Event(new EventName("Meeting"), new EventDate("01-01-2025"),
                new EventCategory("STAFF"))));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        Event event = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        UniqueEventList uniqueEventList = new UniqueEventList();
        uniqueEventList.add(event);
        assertTrue(uniqueEventList.contains(event));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        Event event = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        UniqueEventList uniqueEventList = new UniqueEventList();
        uniqueEventList.add(event);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.add(event));
    }

    @Test
    public void setEvent_nullTargetOrEditedEvent_throwsNullPointerException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, null));
        Event event = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        uniqueEventList.add(event);
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(event, null));
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, event));
    }

    @Test
    public void setEvent_targetNotInList_throwsEventNotFoundException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        Event target = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        Event editedEvent = new Event(new EventName("Conference"), new EventDate("02-01-2025"),
                new EventCategory("STAFF"));
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(target, editedEvent));
    }

    @Test
    public void setEvent_editedEventIsDuplicate_throwsDuplicateEventException() {
        Event event1 = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        Event event2 = new Event(new EventName("Conference"), new EventDate("02-01-2025"), new EventCategory("STAFF"));
        UniqueEventList uniqueEventList = new UniqueEventList();
        uniqueEventList.add(event1);
        uniqueEventList.add(event2);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvent(event1, event2));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        Event event = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(event));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        Event event = new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF"));
        UniqueEventList uniqueEventList = new UniqueEventList();
        uniqueEventList.add(event);
        uniqueEventList.remove(event);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullUniqueEventList_throwsNullPointerException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_listContainsDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(new Event(new EventName("Meeting"),
                        new EventDate("01-01-2025"), new EventCategory("STAFF")),
                new Event(new EventName("Meeting"), new EventDate("01-01-2025"), new EventCategory("STAFF")));
        UniqueEventList uniqueEventList = new UniqueEventList();
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvents(listWithDuplicateEvents));
    }

    @Test
    public void setEvents_listIsNotEmpty_clearsAndAddsEvents() {
        List<Event> listWithEvents = Arrays.asList(new Event(new EventName("Meeting"),
                        new EventDate("01-01-2025"), new EventCategory("STAFF")),
                new Event(new EventName("Conference"), new EventDate("02-01-2025"), new EventCategory("STAFF")));
        UniqueEventList uniqueEventList = new UniqueEventList();
        uniqueEventList.add(new Event(new EventName("Random Event"), new EventDate("03-01-2025"),
                new EventCategory("SPONSOR")));
        uniqueEventList.setEvents(listWithEvents);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.setEvents(listWithEvents);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_listIsEmpty_clearsEvents() {
        List<Event> emptyList = Collections.emptyList();
        UniqueEventList uniqueEventList = new UniqueEventList();
        uniqueEventList.add(new Event(new EventName("Random Event"), new EventDate("03-01-2025"),
                new EventCategory("PARTICIPANT")));
        uniqueEventList.setEvents(emptyList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void equals_sameList_returnsTrue() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        UniqueEventList otherUniqueEventList = new UniqueEventList();
        assertTrue(uniqueEventList.equals(otherUniqueEventList));
    }

    @Test
    public void equals_differentLists_returnsFalse() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        UniqueEventList otherUniqueEventList = new UniqueEventList();
        uniqueEventList.add(new Event(new EventName("Meeting"), new EventDate("01-01-2025"),
                new EventCategory("STAFF")));
        assertFalse(uniqueEventList.equals(otherUniqueEventList));
    }

    @Test
    public void hashCode_sameList_equalHashCodes() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        UniqueEventList otherUniqueEventList = new UniqueEventList();
        assertEquals(uniqueEventList.hashCode(), otherUniqueEventList.hashCode());
    }

    @Test
    public void toString_validUniqueEventList_correctStringRepresentation() {
        UniqueEventList uniqueEventList = new UniqueEventList();
        uniqueEventList.add(new Event(new EventName("Meeting"), new EventDate("01-01-2025"),
                new EventCategory("STAFF")));
        String expectedString = "[eventName=Meeting, eventDate=01-01-2025, eventCategory=STAFF]";
        assertEquals(expectedString, uniqueEventList.toString());
    }
}
