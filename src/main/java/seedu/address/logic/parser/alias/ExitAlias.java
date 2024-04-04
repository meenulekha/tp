package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.ExitCommand;

/**
 * Represents an alias for the "exit" command.
 */
public class ExitAlias extends Alias<ExitCommand> {
    public static final String ALIAS_WORD = "ex";

    @Override
    public ExitCommand parse(String input) {
        return new ExitCommand();
    }
}
