package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LinkCommand;

public class LinkCommandParserTest {
    @Test
    public void parse_oneIndex_success() {
        // single index
        LinkCommandParser parser = new LinkCommandParser();
        assertParseSuccess(parser, "1", new LinkCommand(new int[] {0}));
    }

    @Test
    public void parse_multipleIndexes_success() {
        // multiple indexes
        LinkCommandParser parser = new LinkCommandParser();
        assertParseSuccess(parser, "1 2 3", new LinkCommand(new int[] {0, 1, 2}));
    }

    @Test
    public void parse_invalidIndex_failure() {
        // invalid index
        LinkCommandParser parser = new LinkCommandParser();
        assertParseFailure(parser, "1 a", LinkCommand.MESSAGE_USAGE);
    }
}
