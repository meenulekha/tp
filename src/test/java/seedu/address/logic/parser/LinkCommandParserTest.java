package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkCommand;

public class LinkCommandParserTest {
    @Test
    public void parse_oneIndex_success() {
        // single index
        LinkCommandParser parser = new LinkCommandParser();
        assertParseSuccess(parser, "1", new LinkCommand(new Index[]{Index.fromOneBased(1)}));
    }

    @Test
    public void parse_multipleIndexes_success() {
        // multiple indexes
        LinkCommandParser parser = new LinkCommandParser();
        assertParseSuccess(parser, "1 2 3", new LinkCommand(new Index[]{Index.fromOneBased(1), Index.fromOneBased(2),
                Index.fromOneBased(3)}));
    }

    @Test
    public void parse_invalidIndex_failure() {
        // invalid index
        LinkCommandParser parser = new LinkCommandParser();
        assertParseFailure(parser, "1 a", LinkCommand.MESSAGE_USAGE);
    }
}
