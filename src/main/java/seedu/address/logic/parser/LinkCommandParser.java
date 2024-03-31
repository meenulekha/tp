package seedu.address.logic.parser;

import java.util.Arrays;

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
        Boolean isEmpty = args.trim().isEmpty();
        Boolean isInvalid = !args.trim().matches("\\d+(\\s+\\d+)*");
        if (isEmpty || isInvalid) {
            //checks if the user input is not all integers separated by spaces or empty
            throw new ParseException(LinkCommand.MESSAGE_USAGE);
        }

        int[] indexes = Arrays.stream(args.trim().split("\\s+"))
                                .mapToInt(Integer::parseInt)
                                .map(i -> i - 1)
                                .toArray();

        return new LinkCommand(indexes);
    }

}
