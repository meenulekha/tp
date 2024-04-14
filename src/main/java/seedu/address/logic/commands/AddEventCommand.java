package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTCATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENTNAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.UndoException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an event to the event book.
 */
public class AddEventCommand extends EventCommand implements ReversibleCommand {
    public static final String COMMAND_WORD = "addevent";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the address book. "
            + "Parameters: "
            + PREFIX_EVENTNAME + "EVENT_NAME "
            + PREFIX_EVENTDATE + "EVENT_DATE "
            + PREFIX_EVENTCATEGORY + "EVENT_CATEGORY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENTNAME + "Meeting " + PREFIX_EVENTDATE + "10-10-2024 " + PREFIX_EVENTCATEGORY + "staff";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";
    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_SUCCESS_UNDO = "event deleted: %1$s";
    public static final String MESSAGE_UNDO_NONEXISTENT_EVENT = "Undo failed:"
            + "Person does not exist in the address book";

    private final Event toAdd;



    /**
     * Creates an AddEventCommand to add the specified {@code event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public EventCommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        model.addCommand(this);
        //String formattedMessage = String.format(MESSAGE_SUCCESS, toAdd.getEventName(), toAdd.getEventDate(),
        //      toAdd.getEventCategory());
        return new EventCommandResult(String.format(MESSAGE_SUCCESS, Messages.formatEvent(toAdd)));
        //return new EventCommandResult(formattedMessage);
    }

    @Override
    public CommandResult undo(Model model) throws UndoException {
        requireNonNull(model);

        if (!model.hasEvent(toAdd)) {
            throw new UndoException(MESSAGE_UNDO_NONEXISTENT_EVENT);
        }
        model.deleteEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS_UNDO, Messages.formatEvent(toAdd)));
    }

    @Override
    public CommandResult redo(Model model) throws UndoException, CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatEvent(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddEventCommand)) {
            return false;
        }

        AddEventCommand otherAddCommand = (AddEventCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toAdd", toAdd).toString();
    }



}
