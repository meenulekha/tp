package seedu.address.logic.parser.alias;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.RedoCommand;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class RedoAliasTest {

    @Test
    void parse_returnsRedoCommand() {
        assertParseSuccess(new RedoAlias(), "random input", new RedoCommand());
    }
}