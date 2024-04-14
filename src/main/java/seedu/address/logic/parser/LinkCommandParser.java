package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LinkCommand object
 */
public class LinkCommandParser implements Parser<LinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LinkCommand
     * and returns a LinkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkCommand parse(String args) throws ParseException {
        String trimmed = args.trim();
        Boolean isEmpty = trimmed.isEmpty();
        String validationRegex = "[1-9][0-9]*(\\s+[1-9][0-9]*)*";
        Boolean isInvalid = !trimmed.matches(validationRegex);
        if (isEmpty || isInvalid) {
            //checks if the user input is not all integers separated by spaces or empty
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
        }

        Index[] indexes = Arrays.stream(trimmed.split("\\s+"))
                                .mapToInt(Integer::parseInt)
                                .mapToObj(Index::fromOneBased)
                                .toArray(Index[]::new);

        return new LinkCommand(indexes);
    }

}
