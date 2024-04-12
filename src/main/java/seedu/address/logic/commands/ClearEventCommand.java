package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.EventBook;
import seedu.address.model.Model;

/**
 * Clears the list of events.
 */
public class ClearEventCommand extends EventCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Events have been cleared!";


    @Override
    public EventCommandResult execute(Model model) {
        requireNonNull(model);
        model.setEventBook(new EventBook());
        return new EventCommandResult(MESSAGE_SUCCESS);
    }
}
