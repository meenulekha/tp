package seedu.address.logic.parser.alias;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UndoCommand;


class UndoAliasTest {

    @Test
    void parse() {
        assertParseSuccess(new UndoAlias(), "random input", new UndoCommand());
    }
}
