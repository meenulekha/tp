package seedu.address.model.person;
/**
 * Represents a sponsor in the hackathon.
 */
public class Sponsor extends Person {
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

    @Override
    public boolean isSamePerson(Person other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Sponsor)) {
            return false;
        }
        return super.isSamePerson(other);
    }
}
