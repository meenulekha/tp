package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.Event.Event;
import seedu.address.model.EventBook;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;

public class TypicalEvents {
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253").withCategory("PARTICIPANT").build();
    public static final Event EVENT1 = new EventBuilder().withEventName("Event 1")
            .withEventDate("01/01/2021").withEventCategory("PARTICIPANT").build();
    public static final Event EVENT2 = new EventBuilder().withEventName("Event 2").withEventDate("02/02/2021")
            .withEventCategory("PARTICIPANT").build();
    public static final Event EVENT3 = new EventBuilder().withEventName("Event 3").withEventDate("03/03/2021")
            .withEventCategory("STAFF").build();
    public static final Event EVENT4 = new EventBuilder().withEventName("Event 4").withEventDate("04/04/2021")
            .withEventCategory("SPONSOR").build();
    public static final Event EVENT5 = new EventBuilder().withEventName("Event 5").withEventDate("05/05/2021")
            .withEventCategory("PARTICIPANT").build();
    public static final Event EVENT6 = new EventBuilder().withEventName("Event 6").withEventDate("06/06/2021")
            .withEventCategory("STAFF").build();
    public static final Event EVENT7 = new EventBuilder().withEventName("Event 7").withEventDate("07/07/2021")
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
