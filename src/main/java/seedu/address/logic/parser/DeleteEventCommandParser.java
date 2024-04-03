package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteEventCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteEventCommand
     * and returns a DeleteEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
