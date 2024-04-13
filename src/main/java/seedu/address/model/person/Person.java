package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person(Staff, Sponsor, Participant) in the hackathon.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person implements Identifiable {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Category category;

    // Data fields
    private final Comment comment;

    /**
     * Constructs person without comment. Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Category category) {
        requireAllNonNull(name, phone, email, category);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.category = category;
        this.comment = new Comment();
    }

    /**
     * Constructs person with given comment. Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Category category, Comment comment) {
        requireAllNonNull(name, phone, email, category);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.category = category;
        this.comment = comment;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Category getCategory() {
        return category;
    }

    public Comment getComment() {
        return comment;
    }

    public abstract Group getGroup();

    public abstract void setGroup(Group group);

    public abstract int getGroupNumber();

    public abstract void setGroupNumber(int groupNumber);

    /**
     * Returns true if both persons have the same identity.
     * This defines a weaker notion of equality between two persons.
     * When name is the same, the person is considered the same person if the phone number or email is the same.
     * This method is used for all participant, staff and sponsor.
     * Note: This method ensures that the category of the person is not considered in the comparison.
     */
    @Override
    public boolean isSameIdentity(Identifiable other) {
        if (other == this) {
            // same object -> returns true
            return true;
        }
        if (!(other instanceof Person)) {
            // not person -> returns false
            return false;
        }
        Person otherPerson = (Person) other;
        // check if the person has the same phone number or email
        boolean hasMatch = phone.equals(otherPerson.phone) || email.equals(otherPerson.email);
        return name.equals(otherPerson.name) && hasMatch;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && category.equals(otherPerson.category);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email);
    }

    @Override
    public String toString() {
        return toStringBuilder().toString();
    }

    /**
     * Returns a ToStringBuilder representation of the person's details as a toString() method helper.
     */
    public ToStringBuilder toStringBuilder() {
        return new ToStringBuilder(this).add("name", name).add("phone", phone).add("email", email)
                .add("category", category).add("comment", comment);
    }

    public String toCsvString() {
        return toCsvLine() + "\n";
    }

    /**
     * Returns a string representation of the person's details as a toCsvString() method helper.
     */
    public String toCsvLine() {
        return name + "," + phone + "," + email + "," + comment;
    }

    /**
     * Returns a string representation of the person's details for display.
     */
    public String getInformation() {
        return name + "\n" + phone + "\n" + email + "\n" + category + "\n" + comment + "\n";
    }
}
