package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class SponsorTest {
    @Test
    public void isSameIdentity() {
        // same object -> returns true
        Sponsor sponsor = (Sponsor) new PersonBuilder().withCategory("SPONSOR").build();
        assert (sponsor.isSameIdentity(sponsor));


        assert (!sponsor.isSameIdentity(null));

        // same identity, different category -> returns true
        Staff staff = (Staff) new PersonBuilder().withCategory("STAFF").build();
        assert (sponsor.isSameIdentity(staff));

        Sponsor differentSponsor = (Sponsor) new PersonBuilder().withCategory("SPONSOR").withName("john").build();
        assert (!sponsor.isSameIdentity(differentSponsor));
    }

}
