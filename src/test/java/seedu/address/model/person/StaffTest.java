package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StaffTest {
    @Test
    public void isSamePerson() {
        Staff staff = (Staff) new PersonBuilder().withCategory("STAFF").withName("John").build();
        Staff staffCopy = (Staff) new PersonBuilder().withCategory("STAFF").withName("John").build();
        Staff other = (Staff) new PersonBuilder().withCategory("STAFF").withName("Peter").build();
        assertFalse(staff.isSameIdentity(other));
        assert (staff.isSameIdentity(staffCopy));

        Sponsor sponsor = (Sponsor) new PersonBuilder().withCategory("SPONSOR").withName("Jake").build();
        assertFalse(staff.isSameIdentity(sponsor));

    }

    @Test
    public void getGroupNumber() {
        Staff test = (Staff) new PersonBuilder().withCategory("STAFF").withName("John").build();
        test.setGroupNumber(1);
        assertNotEquals(5, test.getGroupNumber());
        assertEquals(1, test.getGroupNumber());
    }
}

