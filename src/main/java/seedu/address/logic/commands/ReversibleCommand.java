package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.UndoException;
import seedu.address.model.Model;

/**
 * Represents a command that can be undone and redone. For a command to be
 * reversible, {@link Command#execute(Model)} must add the command to the
 * command history in the model.
 */
public interface ReversibleCommand {
    /**
     * Undoes the effects of this command. The command should be added to the
     * command history in the model before this method is called. The command must
     * not be manually removed from the history in this method.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult undo(Model model) throws UndoException;

    /**
     * Executes the command again after undoing it. The command must not be manually
     * added to the history in this method.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult redo(Model model) throws UndoException, CommandException;
}
