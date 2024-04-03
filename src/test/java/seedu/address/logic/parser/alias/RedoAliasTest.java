package seedu.address.logic.parser.alias;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RedoCommand;

class RedoAliasTest {

    @Test
    void parse_returnsRedoCommand() {
        assertParseSuccess(new RedoAlias(), "random input", new RedoCommand());
    }
}
