package seedu.address.logic.commands;

import seedu.address.logic.EventCommand;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class ListEventCommand extends EventCommand {
    public static final String COMMAND_WORD = "listevent";

    public static final String MESSAGE_SUCCESS = "Listed all events";


    @Override
    public EventCommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new EventCommandResult(MESSAGE_SUCCESS);
    }
}
