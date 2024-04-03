package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.RedoCommand;

public class RedoAlias extends Alias<RedoCommand> {
    public static final String ALIAS_WORD = "rd";

    @Override
    public RedoCommand parse(String input) {
        return new RedoCommand();
    }
}
