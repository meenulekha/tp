package seedu.address.model.person;

/**
 * Interface for objects that have an identity.
 */
public interface Identifiable {
    boolean isSameIdentity(Identifiable other);
}
