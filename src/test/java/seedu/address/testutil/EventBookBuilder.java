package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.Event.Event;
import seedu.address.model.EventBook;

public class EventBookBuilder {
    private EventBook eventBook;

    public EventBookBuilder() {
        eventBook = new EventBook();
    }

    public EventBookBuilder(EventBook eventBook) {
        this.eventBook = eventBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public EventBookBuilder withEvent(Event event) {
        eventBook.addEvent(event);
        return this;
    }

    public EventBook build() {
        return eventBook;
    }
}
