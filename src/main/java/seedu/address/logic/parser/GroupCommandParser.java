package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GroupCommand object
 */
public class GroupCommandParser implements Parser<GroupCommand> {

    /**
     * Parses tje given {@code String} of arguments in the context of the GroupCommand
     * and returns a GroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public GroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] trimmedArgs = args.trim().split("\\s");

        Index index;

        try {
            String trimmedIndex = trimmedArgs[0];
            index = ParserUtil.parseIndex(trimmedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE), pe);
        }

        if (trimmedArgs.length < 2) {
            return new GroupCommand(index);
        } else {
            try {
                return new GroupCommand(index, Integer.parseInt(trimmedArgs[1]));
            } catch (NumberFormatException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE), e);
            }
        }
    }

}
