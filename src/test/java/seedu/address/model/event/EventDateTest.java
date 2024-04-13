package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventDateTest {

    @Test
    public void isValidDate_validDate_true() {
        assertTrue(EventDate.isValidDate("01-01-2025"));
    }

    @Test
    public void isValidDate_invalidDate_false() {
        assertFalse(EventDate.isValidDate("32-01-2023")); // Invalid day
        assertFalse(EventDate.isValidDate("01-13-2023")); // Invalid month
        assertFalse(EventDate.isValidDate("01-01-10000")); // Invalid year
        assertFalse(EventDate.isValidDate("2023-01-01")); // Invalid format
        assertFalse(EventDate.isValidDate("")); // Empty string
        assertFalse(EventDate.isValidDate("abc")); // Non-numeric characters
    }

    @Test
    public void isValidDate_pastDate_false() {
        assertFalse(EventDate.isValidDate("01-01-2000")); // Past date
    }

}
