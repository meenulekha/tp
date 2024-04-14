package seedu.address.model.person;

/**
 * Factory class for creating Person objects.
 */
public class PersonFactory {
    /**
     * Creates a Person based on the necessary fields.
     */
    public static Person createPerson(Name name, Phone phone, Email email, Category category) {
        return createPerson(name, phone, email, category, new Comment());
    }

    /**
     * Creates a Person with the category and comment.
     */
    public static Person createPerson(Name name, Phone phone, Email email, Category category, Comment comment) {
        return createPerson(name, phone, email, category, comment, Group.getDefaultGroupForCategory(category.type));
    }

    /**
     * Creates a Person with the category, comment and group.
     */
    public static Person createPerson(Name name, Phone phone, Email email, Category category, Comment comment,
                                      Group group) {
        Person categoryPerson;
        switch (category.type) {
        case PARTICIPANT:
            categoryPerson = new Participant(name, phone, email, category, comment);
            break;
        case STAFF:
            categoryPerson = new Staff(name, phone, email, category, comment);
            break;
        case SPONSOR:
            return new Sponsor(name, phone, email, category, comment);
        default: // cannot happen due to category validation
            throw new IllegalArgumentException(Category.MESSAGE_CONSTRAINTS);
        }
        categoryPerson.setGroup(group);
        return categoryPerson;
    }
}
