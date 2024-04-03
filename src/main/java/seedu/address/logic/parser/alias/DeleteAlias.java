package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteAlias extends Alias<DeleteCommand> {
    public static final String ALIAS_WORD = "d";

    @Override
    public DeleteCommand parse(String input) throws ParseException {
        return new DeleteCommandParser().parse(input);
    }
}
