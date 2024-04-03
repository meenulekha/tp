package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.GroupRandomCommand;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class GroupRandomCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupRandomCommand.MESSAGE_USAGE);

    private GroupRandomCommandParser parser = new GroupRandomCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no maximum group size specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid maximum group size
        assertParseFailure(parser, "some random string", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        int maxGroupSize = 2;
        String userInput = " " + maxGroupSize;

        GroupRandomCommand expectedCommand = new GroupRandomCommand(maxGroupSize);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
