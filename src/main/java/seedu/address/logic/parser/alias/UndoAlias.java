package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.UndoCommand;

public class UndoAlias extends Alias<UndoCommand> {
    public static final String ALIAS_WORD = "ud";

    @Override
    public UndoCommand parse(String input) {
        return new UndoCommand();
    }
}
