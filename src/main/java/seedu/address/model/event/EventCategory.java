package seedu.address.model.event;

import seedu.address.model.person.Categories;
import seedu.address.model.person.Category;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.event.EventName.MESSAGE_CONSTRAINTS;

public class EventCategory {
    public final String value;

    public EventCategory(String eventCategory) {
        requireNonNull(eventCategory);
        checkArgument(isValidCategory(eventCategory), MESSAGE_CONSTRAINTS);
        value = eventCategory;
    }

//    public String getCategory() {
//        return this.value;
//    }

    /**
     * Returns true if a given string is a valid Category.
     */
    public static boolean isValidCategory(String test) {
        return Categories.contains(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Category)) {
            return false;
        }

        Category otherCategory = (Category) other;
        return value.equals(otherCategory.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
