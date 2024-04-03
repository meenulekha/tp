package seedu.address.model.Event;



import java.util.Date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class EventDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates of events should be in DD-MM-YYYY format, and it should not be blank";

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
        return test.matches(VALIDATION_REGEX);
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

