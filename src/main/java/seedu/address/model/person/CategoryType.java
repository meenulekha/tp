package seedu.address.model.person;

/**
 * Enum for three category types of contacts.
 */
public enum CategoryType {
    PARTICIPANT, STAFF, SPONSOR;

    /**
     * @param test string to be tested.
     * @return boolean whether enum contains test.
     */
    public static boolean contains(String test) {
        if (test == null) {
            return false;
        }
        for (CategoryType c : CategoryType.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
