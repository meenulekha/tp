package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.UndoException;
import seedu.address.model.Model;

/**
 * Undoes the most recent command that modifies the address book.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the most recent command that modifies the address book.\n" + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_FAILURE_NO_COMMAND_TO_UNDO = "There's no command to undo.";

    public UndoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canUndoAddressBook()) {
            throw new CommandException(MESSAGE_FAILURE_NO_COMMAND_TO_UNDO);
        }
        try {
            return model.undoAddressBook();
        } catch (UndoException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
