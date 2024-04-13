package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a participants in hackathon.
 * Inherits interface Identifiable.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Participant extends Person {
    private Group group;

    /**
     * Constructs participants. Every field must be present and not null.
     */
    public Participant(Name name, Phone phone, Email email, Category category) {
        super(name, phone, email, category);
        this.group = new Group();
    }

    /**
     * Constructs participants with comment.
     * Every field must be present and not null.
     */
    public Participant(Name name, Phone phone, Email email, Category category, Comment comment) {
        super(name, phone, email, category, comment);
        this.group = new Group();
    }

    /**
     * Sets the group of the participant.
     */
    @Override
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Returns the group of the participant.
     */
    @Override
    public Group getGroup() {
        return group;
    }

    /**
     * Sets the group number of the participant.
     */
    @Override
    public void setGroupNumber(int groupNumber) {
        this.group = new Group(groupNumber);
    }

    /**
     * Returns the group number of the participant.
     */
    @Override
    public int getGroupNumber() {
        return group.getGroupNumber();
    }

    @Override
    public ToStringBuilder toStringBuilder() {
        return super.toStringBuilder().add("group", group);
    }

    @Override
    public String getInformation() {
        return super.getInformation() + group + "\n";
    }
}
