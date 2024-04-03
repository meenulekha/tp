package seedu.address.logic.parser.alias;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

import static org.junit.jupiter.api.Assertions.*;

class AliasParserTest {
    private AliasParser parser = new AliasParser();

    @Test
    void parseAlias_addParticipant() throws ParseException {
        Person person = new PersonBuilder().withCategory("PARTICIPANT").build();
        String args = " " + PersonUtil.getPersonDetailNoCategory(person); // must add whitespace to parse
        AddCommand command = (AddCommand) parser.parseAlias(AddParticipantAlias.ALIAS_WORD, args);

        assertEquals(new AddCommand(person), command);
    }

    @Test
    void parseAlias_addStaff() throws ParseException {
        Person person = new PersonBuilder().withCategory("STAFF").build();
        String args = " " + PersonUtil.getPersonDetailNoCategory(person); // must add whitespace to parse
        AddCommand command = (AddCommand) parser.parseAlias(AddStaffAlias.ALIAS_WORD, args);

        assertEquals(new AddCommand(person), command);
    }

    @Test
    void parseAlias_addSponsor() throws ParseException {
        Person person = new PersonBuilder().withCategory("SPONSOR").build();
        String args = " " + PersonUtil.getPersonDetailNoCategory(person); // must add whitespace to parse
        AddCommand command = (AddCommand) parser.parseAlias(AddSponsorAlias.ALIAS_WORD, args);

        assertEquals(new AddCommand(person), command);
    }
}