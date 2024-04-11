package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Comment;

/**
 * Parses input arguments and creates a new CommentCommand object
 */
public class CommentCommandParser implements Parser<CommentCommand> {
    public static final String MESSAGE_INVALID_COMMENT = "Comment should not be empty";
    public static final String MESSAGE_INVALID_COMMENT_LENGTH = "Comment should not exceed 1000 characters";

    /**
     * Parses the given {@code String} of arguments in the context of the CommentCommand
     * and returns a CommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        String[] splitArgs = trimmedArgs.split("\\s+", 2);
        if (splitArgs.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Comment comment = ParserUtil.parseComment(splitArgs[1]);
            return new CommentCommand(index, comment);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE), pe);
        }
    }
}
