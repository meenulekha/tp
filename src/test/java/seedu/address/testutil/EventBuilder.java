package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventCategory;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventFactory;
import seedu.address.model.event.EventName;


/**
 * A utility class to help with building event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_NAME = "Meeting";
    public static final String DEFAULT_DATE = "05-05-2024";
    public static final String DEFAULT_CATEGORY = "PARTICIPANT";

    private EventName name;
    private EventDate date;
    private EventCategory category;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public EventBuilder() {
        name = new EventName(DEFAULT_NAME);
        date = new EventDate(DEFAULT_DATE);
        category = new EventCategory(DEFAULT_CATEGORY);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getEventName();
        date = eventToCopy.getEventDate();
        category = eventToCopy.getEventCategory();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public EventBuilder withEventName(String name) {
        this.name = new EventName(name);
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public EventBuilder withEventDate(String phone) {
        this.date = new EventDate(phone);
        return this;
    }


    /**
     * Sets the {@code Category} of the {@code Category} that we are building.
     */
    public EventBuilder withEventCategory(String category) {
        this.category = new EventCategory(category);
        return this;
    }

    public Event build() {
        return EventFactory.createEvent(name, date, category);
    }

}
