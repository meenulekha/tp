package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s information matches any of the keywords given.
 */
public class ContactContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ContactContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests that a {@code Person}'s information matches any of the keywords given.
     * @param person the input argument
     * @return true if the person's information contains any of the keywords given
     */
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getInformation(), keyword));
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactContainsKeywordsPredicate)) {
            return false;
        }

        ContactContainsKeywordsPredicate otherPredicate = (ContactContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
