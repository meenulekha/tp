package seedu.address.model.Event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


public class EventName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names of events should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String eventName;


    /**
     * Constructs a {@code Name}.
     *
     * @param eventName A valid name.
     */
    public EventName(String eventName) {
        requireNonNull(eventName);
        checkArgument(isValidName(eventName), MESSAGE_CONSTRAINTS);
        this.eventName = eventName;
    }


    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return eventName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventName)) {
            return false;
        }

        EventName otherName = (EventName) other;
        return eventName.equals(otherName.eventName);
    }

    @Override
    public int hashCode() {
        return eventName.hashCode();
    }

}
