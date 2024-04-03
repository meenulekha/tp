package seedu.address.logic;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EventCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class EventCommand {
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract EventCommandResult execute(Model model) throws CommandException;


}
