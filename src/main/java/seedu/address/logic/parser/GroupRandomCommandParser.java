package seedu.address.logic.parser;

import seedu.address.logic.commands.GroupRandomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new GroupRandomCommand object
 */
public class GroupRandomCommandParser implements Parser<GroupRandomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GroupRandomCommand
     * and returns a GroupRandomCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public GroupRandomCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] trimmedArgs = args.trim().split("\\s");

        int maxGroupSize;

        try {
            String trimmedMaxGroupSize = trimmedArgs[0];
            maxGroupSize = Integer.parseInt(trimmedMaxGroupSize);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupRandomCommand.MESSAGE_USAGE));
        } catch (NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupRandomCommand.MESSAGE_USAGE), e);
        }

        return new GroupRandomCommand(maxGroupSize);
    }

}
