package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an alias for the "edit" command.
 */
public class EditAlias extends Alias<EditCommand> {
    public static final String ALIAS_WORD = "ed";

    @Override
    public EditCommand parse(String input) throws ParseException {
        return new EditCommandParser().parse(input);
    }
}
