package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventNameTest {

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidName_nullName_returnsFalse() {
        assertFalse(EventName.isValidName(null));
    }

    @Test
    public void isValidName_emptyString_returnsFalse() {
        assertFalse(EventName.isValidName(""));
    }

    @Test
    public void isValidName_spacesOnly_returnsFalse() {
        assertFalse(EventName.isValidName("   "));
    }

    @Test
    public void isValidName_nonAlphanumeric_returnsFalse() {
        assertFalse(EventName.isValidName("^$#"));
    }

    @Test
    public void isValidName_containsNonAlphanumeric_returnsFalse() {
        assertFalse(EventName.isValidName("peter*"));
    }

    @Test
    public void isValidName_validName_returnsTrue() {
        assertTrue(EventName.isValidName("Meeting with clients"));
    }

    @Test
    public void isValidName_numbersOnly_returnsTrue() {
        assertTrue(EventName.isValidName("12345"));
    }

    @Test
    public void isValidName_alphanumericWithSpaces_returnsTrue() {
        assertTrue(EventName.isValidName("Meeting 123 with Clients"));
    }

    @Test
    public void isValidName_withSpecialCharacters_returnsFalse() {
        assertFalse(EventName.isValidName("Meeting #123 with Clients"));
    }

    @Test
    public void equals_sameInstance_returnsTrue() {
        EventName name = new EventName("Meeting");
        assertTrue(name.equals(name));
    }

    @Test
    public void equals_null_returnsFalse() {
        EventName name = new EventName("Meeting");
        assertFalse(name.equals(null));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        EventName name = new EventName("Meeting");
        assertFalse(name.equals(5.0f));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        EventName name1 = new EventName("Meeting");
        EventName name2 = new EventName("Conference");
        assertFalse(name1.equals(name2));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        EventName name1 = new EventName("Meeting");
        EventName name2 = new EventName("Meeting");
        assertTrue(name1.equals(name2));
    }
}

