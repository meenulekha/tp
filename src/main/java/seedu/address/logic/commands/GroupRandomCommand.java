package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Category;
import seedu.address.model.person.Group;
import seedu.address.model.person.Participant;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;

/**
 * Sets randomly the group of every person displayed on the list.
 */
public class GroupRandomCommand extends Command implements ReversibleCommand {

    public static final String COMMAND_WORD = "grouprandom";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets randomly the group of every person displayed on the list."
            + "Existing values will be overwritten by the input values. \n"
            + "Parameters: MAX_GROUP_SIZE (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 4";

    public static final String MESSAGE_GROUP_RANDOM_SUCCESS = "Grouped every person on the list";

    public static final String MESSAGE_INVALID_MAX_GROUP_SIZE = "MAX_GROUP_SIZE must be a positive integer";
    public static final String MESSAGE_SUCCESS_UNDO = "Changes reverted.";

    private final int maxGroupSize;

    private List<Integer> originalGroups = new ArrayList<>();
    private List<Integer> randomGroups = new ArrayList<>();
    private List<Person> noSponsorList;

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

        noSponsorList = lastShownList.stream()
                .filter(person -> !person.getCategory().equals(new Category("SPONSOR")))
                .collect(Collectors.toList());

        int numberOfGroup = (int) Math.ceil((double) noSponsorList.size() / maxGroupSize);
        Group.setTotalGroupNumber(Math.max(numberOfGroup, Group.getTotalGroupNumber()));

        int[] groupSizes = new int[numberOfGroup];
        Arrays.fill(groupSizes, 0);

        Random random = new Random();

        for (Person personToGroup : noSponsorList) {
            int targetGroupNumber = random.nextInt(numberOfGroup) + 1;

            while (groupSizes[targetGroupNumber - 1] >= maxGroupSize) {
                targetGroupNumber = random.nextInt(numberOfGroup) + 1;
            }

            groupSizes[targetGroupNumber - 1] += 1;

            if (personToGroup instanceof Staff) {
                Staff staffToGroup = (Staff) personToGroup;
                originalGroups.add(staffToGroup.getGroupNumber());
                staffToGroup.setGroupNumber(targetGroupNumber);
                model.setPerson(personToGroup, staffToGroup);
            } else if (personToGroup instanceof Participant) {
                Participant participantToGroup = (Participant) personToGroup;
                originalGroups.add(participantToGroup.getGroupNumber());
                participantToGroup.setGroupNumber(targetGroupNumber);
                model.setPerson(personToGroup, participantToGroup);
            }

            randomGroups.add(targetGroupNumber);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        model.addCommand(this);
        return new CommandResult(String.format(MESSAGE_GROUP_RANDOM_SUCCESS));
    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);

        for (int i = 0; i < noSponsorList.size(); i++) {
            Person groupedPerson = noSponsorList.get(i);
            int originalGroupNumber = originalGroups.get(i);

            model.groupPerson(groupedPerson, originalGroupNumber);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(String.format(GroupRandomCommand.MESSAGE_SUCCESS_UNDO));
    }

    @Override
    public CommandResult redo(Model model) throws CommandException {
        requireNonNull(model);

        for (int i = 0; i < noSponsorList.size(); i++) {
            Person groupedPerson = noSponsorList.get(i);
            int randomGroupNumber = randomGroups.get(i);

            model.groupPerson(groupedPerson, randomGroupNumber);
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
