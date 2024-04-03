package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindAlias extends Alias<FindCommand> {
    public static final String ALIAS_WORD = "f";

    @Override
    public FindCommand parse(String input) throws ParseException {
        return new FindCommandParser().parse(input);
    }
}
