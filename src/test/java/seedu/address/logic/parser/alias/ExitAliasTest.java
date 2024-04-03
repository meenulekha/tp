package seedu.address.logic.parser.alias;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;

class ExitAliasTest {
    @Test
    public void parse_returnExitCommand() {
        ExitAlias parser = new ExitAlias();
        assertParseSuccess(parser, "some random string", new ExitCommand());
    }
}
