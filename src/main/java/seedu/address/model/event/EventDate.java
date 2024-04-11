package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date for an Event in the event book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class EventDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates of events should be in DD-MM-YYYY format, and it should not be blank. "
                    + "Ensure that the date is in the future.";

    public static final String VALIDATION_REGEX = "^[0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2}$";
    public final String eventDate;


    /**
     * Constructs a {@code Date}.
     *
     * @param eventDate A valid date.
     */
    public EventDate(String eventDate) {
        requireNonNull(eventDate);
        checkArgument(isValidDate(eventDate), MESSAGE_CONSTRAINTS);
        this.eventDate = eventDate;
    }


    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidDate(String test) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate date = LocalDate.parse(test, formatter);
            return !date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
        //return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventDate)) {
            return false;
        }

        EventDate otherDate = (EventDate) other;
        return eventDate.equals(otherDate.eventDate);
    }


    @Override
    public String toString() {
        return eventDate;
    }


    @Override
    public int hashCode() {
        return eventDate.hashCode();
    }

}

