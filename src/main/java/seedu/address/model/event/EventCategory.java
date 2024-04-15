package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.event.EventName.MESSAGE_CONSTRAINTS;

import seedu.address.model.person.CategoryType;

/**
 * Represents an Event's category in the address book.
 */
public class EventCategory {
    public final String value;

    /**
     * Constructs an EventCategory object with the specified category value.
     *
     * @param eventCategory The category value of the event.
     * @throws NullPointerException If the given category value is null.
     * @throws IllegalArgumentException If the given category value is invalid.
     *                                  The error message will contain details about the constraints.
     */
    public EventCategory(String eventCategory) {
        requireNonNull(eventCategory);
        checkArgument(isValidCategory(eventCategory), MESSAGE_CONSTRAINTS);
        value = eventCategory;
    }

    /**
     * Returns true if a given string is a valid Category.
     */
    public static boolean isValidCategory(String test) {
        return CategoryType.contains(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCategory)) {
            return false;
        }

        EventCategory otherCategory = (EventCategory) other;
        return value.equals(otherCategory.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
