package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Exports all selected participants to the event.
 */
public class LinkCommand extends Command {

    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links all selected participants to the event. "
            + "Parameters: multiple index (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_SUCCESS = "Exported all selected participants";

    public final int[] indexes;

    /**
     * Creates a LinkCommand to link the specified {@code Person} to the Sponsors.
     *
     * @param indexes the indexes of the selected participants
     */
    public LinkCommand(int[] indexes) {
        requireNonNull(indexes);
        this.indexes = indexes;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Path filePath = Path.of("./selectedParticipants/list.csv");
        try {
            FileUtil.createFile(filePath);
            String header = "Name, Phone, Email, Tags\n";
            FileUtil.writeToFile(filePath, header);
        } catch (IOException e) {
            throw new CommandException(Messages.MESSAGE_IO_ERROR);
        }
        for (int i = 0; i < indexes.length; i++) {
            List<Person> lastShownList = model.getFilteredPersonList();
            int index = indexes[i];

            if (index < 0 || index >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            for (int j = i + 1; j < indexes.length; j++) {
                if (index == indexes[j]) {
                    //checks if the user input has duplicate indexes
                    throw new CommandException(Messages.MESSAGE_DUPLICATE_INDEX);
                }
            }

            try {
                FileUtil.appendToFile(filePath, lastShownList.get(index).toCsvString());
            } catch (IOException e) {
                throw new CommandException(Messages.MESSAGE_IO_ERROR);
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LinkCommand)) {
            return false;
        }

        LinkCommand otherLinkCommand = (LinkCommand) other;
        return Arrays.equals(indexes, otherLinkCommand.indexes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("indexes", indexes)
                .toString();
    }
}
