package seedu.address.logic.parser.alias;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.UndoCommand;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class UndoAliasTest {

    @Test
    void parse() {
        assertParseSuccess(new UndoAlias(), "random input", new UndoCommand());
    }
}