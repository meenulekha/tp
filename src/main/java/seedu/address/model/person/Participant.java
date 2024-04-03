package seedu.address.model.person;
/**
 * Represents a participants in hackathon.
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
     * Sets the group number of the participant.
     */
    public void setGroupNumber(int groupNumber) {
        this.group = new Group(groupNumber);
    }

     /**
     * Constructs participants with comment. Every field must be present and not null.
     */
    public Participant(Name name, Phone phone, Email email, Category category, Comment comment) {
        super(name, phone, email, category, comment);
        this.group = new Group();
    }

    /**
     * Returns the group number of the participant.
     */
    public int getGroupNumber() {
        return group.getGroupNumber();
    }

    @Override
    public boolean isSamePerson(Person other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Participant)) {
            return false;
        }

        return super.isSamePerson(other);
    }
}
