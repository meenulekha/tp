package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.Parser;

/**
 * Represents an alias for a command.
 */
public abstract class Alias<T extends Command> implements Parser<T> {
}
