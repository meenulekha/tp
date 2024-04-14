package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

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
        String validationRegex = "\\d+(\\s+\\d+)*";
        Boolean isInvalid = !trimmed.matches(validationRegex);
        if (isEmpty || isInvalid) {
            //checks if the user input is not all integers separated by spaces or empty
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
        }
        Index[] indexes = parseIndexes(trimmed);
        return new LinkCommand(indexes);
    }

    /**
     * Returns an array of Indexes from the given {@code String}.
     */
    public static Index[] parseIndexes(String trimmed) throws ParseException {
        List<Index> list = new ArrayList<>();
        for (String s : trimmed.split("\\s+")) {
            try {
                Index index = ParserUtil.parseIndex(s);
                list.add(index);
            } catch (ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
            }
        }
        return list.toArray(Index[]::new);
    }
}
