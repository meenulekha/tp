package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command that can be undone and redone.
 */
public interface ReversibleCommand {
    /**
     * Undoes the effects of this command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult undo(Model model) throws CommandException;

    /**
     * Executes the command again after undoing it.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult redo(Model model) throws CommandException;
}
