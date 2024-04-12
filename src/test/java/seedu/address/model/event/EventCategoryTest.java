package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventCategoryTest {

    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventCategory(null));
    }

    @Test
    public void constructor_invalidCategory_throwsIllegalArgumentException() {
        String invalidCategory = "InvalidCategory";
        assertThrows(IllegalArgumentException.class, () -> new EventCategory(invalidCategory));
    }

    @Test
    public void isValidCategory_nullCategory_returnsFalse() {
        assertFalse(EventCategory.isValidCategory(null));
    }

    @Test
    public void isValidCategory_invalidCategory_returnsFalse() {
        assertFalse(EventCategory.isValidCategory("InvalidCategory"));
    }

    @Test
    public void isValidCategory_validCategory_returnsTrue() {
        assertTrue(EventCategory.isValidCategory("STAFF"));
    }

    @Test
    public void toString_validCategory_returnsSameValue() {
        String categoryValue = "PARTICIPANT";
        EventCategory category = new EventCategory(categoryValue);
        assertEquals(categoryValue, category.toString());
    }

    @Test
    public void equals_sameInstance_returnsTrue() {
        EventCategory category = new EventCategory("STAFF");
        assertTrue(category.equals(category));
    }

    @Test
    public void equals_null_returnsFalse() {
        EventCategory category = new EventCategory("STAFF");
        assertFalse(category.equals(null));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        EventCategory category = new EventCategory("STAFF");
        assertFalse(category.equals(5.0f));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        EventCategory category1 = new EventCategory("STAFF");
        EventCategory category2 = new EventCategory("SPONSOR");
        assertFalse(category1.equals(category2));
    }

    @Test
    public void hashCode_sameValues_returnEqualHashCodes() {
        EventCategory category1 = new EventCategory("SPONSOR");
        EventCategory category2 = new EventCategory("SPONSOR");
        assertEquals(category1.hashCode(), category2.hashCode());
    }
}

