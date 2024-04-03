package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.RedoCommand;

/**
 * Represents an alias for the "redo" command.
 */
public class RedoAlias extends Alias<RedoCommand> {
    public static final String ALIAS_WORD = "rd";

    @Override
    public RedoCommand parse(String input) {
        return new RedoCommand();
    }
}
