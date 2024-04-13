package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's comment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidComment(String)}
 */
public class Comment {
    public static final String MESSAGE_CONSTRAINTS = "Comment can take any values, and it should not be blank";
    /**
     * The first character of the comment must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * The parser will trim the comment before storing it.
     */
    public static final String VALIDATION_REGEX = "\\S.*";

    public static final String DEFAULT_COMMENT = "No comment provided.";

    public final String value;

    /**
     * Constructs a {@code Comment} with the default comment.
     */
    public Comment() {
        value = DEFAULT_COMMENT;
    }

    /**
     * Constructs an {@code Comment}.
     *
     * @param comment A valid comment.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        checkArgument(isValidComment(comment), MESSAGE_CONSTRAINTS);
        value = comment;
    }

    /**
     * Returns true if a given string is a valid comment.
     */
    public static boolean isValidComment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean hasComment() {
        return !value.equals(DEFAULT_COMMENT);
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
        if (!(other instanceof Comment)) {
            return false;
        }

        Comment otherComment = (Comment) other;
        return value.equals(otherComment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
