package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Event.EventNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindEventCommandParser implements EventParser<FindEventCommand>{
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindEventCommand(new EventNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
