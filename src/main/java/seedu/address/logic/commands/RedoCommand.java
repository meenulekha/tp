package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.UndoException;
import seedu.address.model.Model;

/**
 * Redoes the most recent undone command that modifies the address book.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redoes the most recent undone command that modifies the database.";
    public static final String MESSAGE_FAILURE_NO_COMMAND_TO_REDO = "There is no command to redo.";

    public RedoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_FAILURE_NO_COMMAND_TO_REDO);
        }
        try {
            return model.redoAddressBook();
        } catch (UndoException e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RedoCommand;
    }

}
