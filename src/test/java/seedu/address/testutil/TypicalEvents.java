package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EventBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event EVENT1 = new EventBuilder().withEventName("event 1")
            .withEventDate("01-01-2025").withEventCategory("PARTICIPANT").build();
    public static final Event EVENT2 = new EventBuilder().withEventName("event 2").withEventDate("02-02-2025")
            .withEventCategory("PARTICIPANT").build();
    public static final Event EVENT3 = new EventBuilder().withEventName("event 3").withEventDate("03-03-2025")
            .withEventCategory("STAFF").build();
    public static final Event EVENT4 = new EventBuilder().withEventName("event 4").withEventDate("04-04-2025")
            .withEventCategory("SPONSOR").build();
    public static final Event EVENT5 = new EventBuilder().withEventName("event 5").withEventDate("05-05-2025")
            .withEventCategory("PARTICIPANT").build();
    public static final Event EVENT6 = new EventBuilder().withEventName("event 6").withEventDate("06-06-2025")
            .withEventCategory("STAFF").build();
    public static final Event EVENT7 = new EventBuilder().withEventName("event 7").withEventDate("07-07-2025")
            .withEventCategory("SPONSOR").build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEvents() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static EventBook getTypicalEventBook() {
        EventBook eb = new EventBook();
        for (Event event : getTypicalEvents()) {
            eb.addEvent(event);
        }
        return eb;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT1, EVENT2, EVENT3, EVENT4, EVENT5, EVENT6, EVENT7));
    }
}
