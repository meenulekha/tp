package seedu.address.model.person;

import seedu.address.model.person.exceptions.GroupSponsorException;

/**
 * Represents a sponsor in the hackathon.
 * Inherits interface Identifiable.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Sponsor extends Person {
    public static final int INVALID_GROUP_NUMBER = -1;

    /**
     * Constructs a sponsor. Every field must be present and not null.
     */
    public Sponsor(Name name, Phone phone, Email email, Category category) {
        super(name, phone, email, category);
    }

    /**
     * Constructs a sponsor with comment. Every field must be present and not null.
     */
    public Sponsor(Name name, Phone phone, Email email, Category category, Comment comment) {
        super(name, phone, email, category, comment);
    }

    /**
     * Throws GroupSponsorException.
     */
    @Override
    public void setGroup(Group group) {
        throw new GroupSponsorException();
    }

    /**
     * Returns null.
     */
    @Override
    public Group getGroup() {
        return null;
    }

    /**
     * Throws GroupSponsorException.
     */
    public void setGroupNumber(int groupNumber) {
        throw new GroupSponsorException();
    }

    /**
     * Returns invalid group number.
     */
    public int getGroupNumber() {
        return INVALID_GROUP_NUMBER;
    }
}
