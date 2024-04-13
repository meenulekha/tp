package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
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

    /**
     * Creates a ViewCommand to view the comment of the person at the specified {@code Index}.
     *
     * @param index of the person in the filtered person list to view
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    /**
     * Executes the ViewCommand to view the comment of the person at the specified {@code Index}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the CommandResult of the execution
     * @throws CommandException if the index is invalid
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        validateIndex(index, lastShownList);

        Person personToView = model.getFilteredPersonList().get(index.getZeroBased());
        String comment = personToView.getComment().toString();
        String result = formatSuccessMessage(personToView) + comment;
        return new CommandResult(result);
    }

    /**
     * Validates the index of the person to view.
     *
     * @param index of the person to view
     * @param lastShownList the list of persons to view from
     * @throws CommandException if the index is invalid
     */
    public void validateIndex(Index index, List<Person> lastShownList) throws CommandException {
        boolean isLargerThanListSize = index.getZeroBased() >= lastShownList.size();
        if (isLargerThanListSize) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    private String formatSuccessMessage(Person personToView) {
        return String.format(MESSAGE_VIEW_COMMENT_SUCCESS, Messages.format(personToView));
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
