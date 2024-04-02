package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Sets randomly the group of every person displayed on the list.
 */
public class GroupRandomCommand extends Command {

    public static final String COMMAND_WORD = "grouprandom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets randomly the group of every person displayed on the list."
            + "Existing values will be overwritten by the input values. \n"
            + "Parameters: MAX_GROUP_SIZE (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 4";

    public static final String MESSAGE_GROUP_RANDOM_SUCCESS = "Grouped every person on the list";

    public static final String MESSAGE_INVALID_MAX_GROUP_SIZE = "MAX_GROUP_SIZE must be a positive integer";

    private final int maxGroupSize;

    public GroupRandomCommand(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (maxGroupSize < 1) {
            throw new CommandException(MESSAGE_INVALID_MAX_GROUP_SIZE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        List<Person> noSponsorList = lastShownList.stream()
                .filter(person -> !person.getCategory().equals(new Category("SPONSOR")))
                .collect(Collectors.toList());

        int numberOfGroup = (int) Math.ceil((double) noSponsorList.size() / maxGroupSize);

        int[] groupSizes = new int[numberOfGroup];
        Arrays.fill(groupSizes, 0);

        Random random = new Random();

        for (Person personToGroup : noSponsorList) {
            int targetGroupNumber = random.nextInt(numberOfGroup) + 1;

            while (groupSizes[targetGroupNumber - 1] >= maxGroupSize) {
                targetGroupNumber = random.nextInt(numberOfGroup) + 1;
            }

            if (personToGroup instanceof Staff) {
                Staff staffToGroup = (Staff) personToGroup;
                staffToGroup.setGroupNumber(targetGroupNumber);
                model.setPerson(personToGroup, staffToGroup);
            } else if (personToGroup instanceof Participant) {
                Participant participantToGroup = (Participant) personToGroup;
                participantToGroup.setGroupNumber(targetGroupNumber);
                model.setPerson(personToGroup, participantToGroup);
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(String.format(MESSAGE_GROUP_RANDOM_SUCCESS));
    }

    @Override public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GroupRandomCommand)) {
            return false;
        }

        GroupRandomCommand otherGroupCommand = (GroupRandomCommand) other;
        return maxGroupSize == otherGroupCommand.maxGroupSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("maxGroupSize", maxGroupSize)
                .toString();
    }
}
