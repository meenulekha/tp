package seedu.address.logic.parser.alias;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input with command aliases.
 */
public class AliasParser {

    /**
     * Parses user input into command for execution.
     *
     * @param commandWord command word
     * @param arguments   arguments
     * @return the command based on the user input, or null if the command word is
     *         not an alias
     * @throws ParseException if the arguments does not conform the expected format
     */
    public Command parseAlias(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case AddParticipantAlias.ALIAS_WORD:
            return new AddParticipantAlias().parse(arguments);

        case AddStaffAlias.ALIAS_WORD:
            return new AddStaffAlias().parse(arguments);

        case AddSponsorAlias.ALIAS_WORD:
            return new AddSponsorAlias().parse(arguments);

        default:
            return null;
        }
    }
}
