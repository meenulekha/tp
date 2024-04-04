package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Deletes an event identified using it's displayed index from the event book.
 */
public class DeleteEventCommand extends EventCommand implements ReversibleCommand {
    public static final String COMMAND_WORD = "deleteevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted event: %1$s";
    public static final String MESSAGE_SUCCESS_UNDO = "Undo deleting: event added: %1$s";

    private final Index targetIndex;

    private Event eventToDelete;

    public DeleteEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public EventCommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }
        Event eventToDelete = lastShownList.get(targetIndex.getZeroBased());
        this.eventToDelete = eventToDelete;
        model.deleteEvent(eventToDelete);
        model.addCommand(this);
        return new EventCommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, Messages.formatEvent(eventToDelete)));
    }
    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        model.addEvent(eventToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS_UNDO, Messages.formatEvent(eventToDelete)));
    }
    @Override
    public CommandResult redo(Model model) {
        requireNonNull(model);
        model.deleteEvent(eventToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, Messages.formatEvent(eventToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteEventCommand otherDeleteEventCommand = (DeleteEventCommand) other;
        return targetIndex.equals(otherDeleteEventCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("targetIndex", targetIndex).toString();
    }
}
