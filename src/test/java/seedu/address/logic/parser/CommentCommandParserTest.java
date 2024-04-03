package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommentCommand;
import seedu.address.model.person.Comment;

public class CommentCommandParserTest {
    private CommentCommandParser parser = new CommentCommandParser();
    @Test
    public void parse_validArgs_returnsCommentCommand() {
        assertParseSuccess(parser, "1 This is a comment.",
                            new CommentCommand(INDEX_FIRST_PERSON, new Comment("This is a comment.")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE));
    }
}
