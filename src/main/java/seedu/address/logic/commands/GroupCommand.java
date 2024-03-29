package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;

import java.util.List;
import java.util.Random;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Changes the group of a person identified by the index number used in the displayed person list.
 */
public class GroupCommand extends Command {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the group of a person identified by the index number used in the displayed person list."
            + "Existing values will be overwritten by the input values. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "NEW_GROUP (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2" + " 3";

    public static final String MESSAGE_GROUP_PERSON_SUCCESS = "Grouped Person: %1$s";

    public static final String MESSAGE_PERSON_WITHOUT_GROUP = "Sponsor doesn't have a group";

    private final Index targetIndex;

    private final int targetGroupNumber;

    public GroupCommand(Index targetIndex) {
        Random random = new Random();

        this.targetIndex = targetIndex;
        this.targetGroupNumber = random.nextInt(Group.getTotalGroupNumber());

    }

    public GroupCommand(Index targetIndex, int targetGroupNumber) {
        this.targetIndex = targetIndex;
        this.targetGroupNumber = targetGroupNumber;
        Group.setTotalGroupNumber(targetGroupNumber);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToGroup = lastShownList.get(targetIndex.getZeroBased());

        if (personToGroup instanceof Sponsor) {
            throw new CommandException(MESSAGE_PERSON_WITHOUT_GROUP);
        } else if (personToGroup instanceof Staff) {
            Staff staffToGroup = (Staff) personToGroup;
            staffToGroup.setGroupNumber(targetGroupNumber);
            model.setPerson(personToGroup, staffToGroup);
        } else if (personToGroup instanceof Participant) {
            Participant participantToGroup = (Participant) personToGroup;
            participantToGroup.setGroupNumber(targetGroupNumber);
            model.setPerson(personToGroup, participantToGroup);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_GROUP_PERSON_SUCCESS, Messages.format(personToGroup)));
    }

    @Override public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GroupCommand)) {
            return false;
        }

        GroupCommand otherGroupCommand = (GroupCommand) other;
        return targetIndex.equals(otherGroupCommand.targetIndex)
                && (targetGroupNumber == otherGroupCommand.targetGroupNumber);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("targetGroup", targetGroupNumber)
                .toString();
    }
}
