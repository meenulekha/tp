package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.Sponsor;

/**
 * Changes the group of a person identified by the index number used in the displayed person list.
 */
public class GroupCommand extends Command implements ReversibleCommand {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the group of a person identified by the index number used in the displayed person list."
            + "Existing values will be overwritten by the input values. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "NEW_GROUP (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2" + " 3";

    public static final String MESSAGE_GROUP_PERSON_SUCCESS = "Grouped Person: %1$s";

    public static final String MESSAGE_PERSON_WITHOUT_GROUP = "Sponsor doesn't have a group";
    public static final String MESSAGE_NO_EXISTING_GROUP =
            "Cannot group without a group number when there is no existing group";
    public static final String MESSAGE_SUCCESS_UNDO = "Changes reverted: %1$s";

    private final Index targetIndex;

    private final Optional<Integer> targetGroupNumber;

    private int originalGroupNumber;
    private int finalGroupNumber;
    private Person groupedPerson;

    /**
     * Groups person with index {@code targetIndex} into random available groups.
     * @param targetIndex of the person in the filtered person list to group.
     */
    public GroupCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetGroupNumber = Optional.empty();

    }

    /**
     * Groups person with index {@code targetIndex} into {@code targetGroupNumber}.
     * @param targetIndex of the person in the filtered person list to group
     * @param targetGroupNumber number to group the person into
     */
    public GroupCommand(Index targetIndex, int targetGroupNumber) {
        this.targetIndex = targetIndex;
        this.targetGroupNumber = Optional.of(targetGroupNumber);
        finalGroupNumber = targetGroupNumber;
        Group.setTotalGroupNumber(Math.max(targetGroupNumber, Group.getTotalGroupNumber()));
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

        }

        originalGroupNumber = personToGroup.getGroupNumber();
        groupedPerson = personToGroup;

        if (targetGroupNumber.isPresent()) {
            groupedPerson.setGroupNumber(targetGroupNumber.get());
        } else if (Group.getTotalGroupNumber() <= 0) {
            throw new CommandException(MESSAGE_NO_EXISTING_GROUP);
        } else {
            Random random = new Random();
            finalGroupNumber = random.nextInt(Group.getTotalGroupNumber()) + 1;
            groupedPerson.setGroupNumber(finalGroupNumber);
        }

        model.setPerson(personToGroup, groupedPerson);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.addCommand(this);
        return new CommandResult(String.format(MESSAGE_GROUP_PERSON_SUCCESS, Messages.format(personToGroup)));
    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);

        model.groupPerson(groupedPerson, originalGroupNumber);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(GroupCommand.MESSAGE_SUCCESS_UNDO, Messages.format(groupedPerson)));
    }

    @Override
    public CommandResult redo(Model model) throws CommandException {
        requireNonNull(model);

        model.groupPerson(groupedPerson, finalGroupNumber);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_GROUP_PERSON_SUCCESS, Messages.format(groupedPerson)));
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
                && (targetGroupNumber.equals(otherGroupCommand.targetGroupNumber));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("targetGroup", finalGroupNumber)
                .toString();
    }
}
