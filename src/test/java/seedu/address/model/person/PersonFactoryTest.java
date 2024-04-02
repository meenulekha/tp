package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class PersonFactoryTest {
    @Test
    public void createPerson() {
        assertTrue(PersonFactory.createPerson(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                new Category("STAFF")) instanceof Staff);

        assertTrue(PersonFactory.createPerson(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                new Category("PARTICIPANT")) instanceof Participant);

        assertTrue(PersonFactory.createPerson(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                new Category("SPONSOR")) instanceof Sponsor);
    }
}
