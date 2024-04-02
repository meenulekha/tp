package seedu.address.model.person;


/**
 * Factory class for creating Person objects.
 */
public class PersonFactory {
    /**
     * Creates a Person based on the category.
     */
    public static Person createPerson(Name name, Phone phone, Email email, Category category) {
        switch (category.value) {
        case "PARTICIPANT":
            return new Participant(name, phone, email, category);
        case "STAFF":
            return new Staff(name, phone, email, category);
        case "SPONSOR":
            return new Sponsor(name, phone, email, category);
        default: // cannot happen due to category validation
            throw new IllegalArgumentException("Invalid category");
        }
    }
}
