package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Views the comment of the person selected by the index number used in the displayed person list.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the comment of the person selected "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_COMMENT_SUCCESS = "Viewing comment of Person: %1$s\n";

    private final Index index;

    public ViewCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        Person personToView = model.getFilteredPersonList().get(index.getZeroBased());
        String comment = personToView.getComment().toString();
        String successMessage = String.format(MESSAGE_VIEW_COMMENT_SUCCESS, Messages.format(personToView));
        String result = successMessage + comment;
        return new CommandResult(result);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return index.equals(otherViewCommand.index);
    }

    @Override
    public String toString() {
        return "Viewing comment of Person: " + index.getOneBased();
    }
}
