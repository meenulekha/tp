package seedu.address.logic.parser.alias;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.FindCommandParser;
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

    @Test
    void parseAlias_delete() throws ParseException {
        String args = " 1";
        assertEquals(parser.parseAlias(DeleteAlias.ALIAS_WORD, args), new DeleteCommandParser().parse(args));
    }

    @Test
    void parseAlias_edit() throws ParseException {
        String args = " 1 " + PersonUtil.getPersonDetailNoCategory(new PersonBuilder().build());
        assertEquals(parser.parseAlias(EditAlias.ALIAS_WORD, args), new EditAlias().parse(args));
    }

    @Test
    void parseAlias_exit() throws ParseException {
        assertEquals(parser.parseAlias(ExitAlias.ALIAS_WORD, ""), new ExitCommand());
    }

    @Test
    void parseAlias_invalidAlias() throws ParseException {
        assertNull(parser.parseAlias("add", ""));
    }

    @Test
    void parseAlias_find() throws ParseException {
        String args = "John";
        assertEquals(parser.parseAlias(FindAlias.ALIAS_WORD, args), new FindCommandParser().parse(args));
    }

    @Test
    void parseAlias_undo() throws ParseException {
        assertEquals(parser.parseAlias(UndoAlias.ALIAS_WORD, ""), new UndoCommand());
    }

    @Test
    void parseAlias_redo() throws ParseException {
        assertEquals(parser.parseAlias(RedoAlias.ALIAS_WORD, ""), new RedoCommand());
    }

}