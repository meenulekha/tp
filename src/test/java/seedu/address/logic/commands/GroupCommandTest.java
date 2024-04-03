package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
public class GroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_success() {
        Person personToGroup = new PersonBuilder().build();
        model.setPerson(model.getFilteredPersonList().get(0), personToGroup);

        GroupCommand groupCommand = new GroupCommand(INDEX_FIRST_PERSON, 2);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_PERSON_SUCCESS,
                Messages.format(personToGroup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToGroup);
        expectedModel.groupPerson(personToGroup, 2);

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noTargetGroup_success() {
        Person personToGroup = new PersonBuilder().build();
        model.setPerson(model.getFilteredPersonList().get(0), personToGroup);

        GroupCommand groupCommandToSetTotalGroupNumber = new GroupCommand(INDEX_FIRST_PERSON, 3);
        GroupCommand groupCommand = new GroupCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_PERSON_SUCCESS,
                Messages.format(personToGroup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToGroup);

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        GroupCommand groupCommand = new GroupCommand(outOfBoundIndex, 2);

        assertCommandFailure(groupCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_groupSponsor_failure() {
        Person personToGroup = new PersonBuilder().withCategory("SPONSOR").build();
        model.setPerson(model.getFilteredPersonList().get(0), personToGroup);

        GroupCommand groupCommand = new GroupCommand(INDEX_FIRST_PERSON, 2);

        assertCommandFailure(groupCommand, model, GroupCommand.MESSAGE_PERSON_WITHOUT_GROUP);
    }

    @Test
    public void execute_groupStaff_success() {
        Person personToGroup = new PersonBuilder().withCategory("STAFF").build();
        model.setPerson(model.getFilteredPersonList().get(0), personToGroup);

        GroupCommand groupCommand = new GroupCommand(INDEX_FIRST_PERSON, 2);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_PERSON_SUCCESS,
                Messages.format(personToGroup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToGroup);
        expectedModel.groupPerson(personToGroup, 2);

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_groupParticipant_success() {
        Person personToGroup = new PersonBuilder().withCategory("PARTICIPANT").build();
        model.setPerson(model.getFilteredPersonList().get(0), personToGroup);

        GroupCommand groupCommand = new GroupCommand(INDEX_FIRST_PERSON, 2);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_PERSON_SUCCESS,
                Messages.format(personToGroup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToGroup);
        expectedModel.groupPerson(personToGroup, 2);

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeUndoRedo_validIndex_success() throws Exception {
        Person personToGroup = new PersonBuilder().withCategory("PARTICIPANT").build();
        model.setPerson(model.getFilteredPersonList().get(0), personToGroup);

        GroupCommand previousGroupCommand = new GroupCommand(INDEX_FIRST_PERSON, 4);
        GroupCommand groupCommand = new GroupCommand(INDEX_FIRST_PERSON, 2);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), personToGroup);
        expectedModel.groupPerson(personToGroup, 2);

        // first person -> group 4
        previousGroupCommand.execute(model);

        // first person -> group 2
        groupCommand.execute(model);

        // undo -> reverts addressbook back to previous state
        expectedModel.groupPerson(personToGroup, 4);
        assertCommandSuccess(new UndoCommand(), model,
                String.format(GroupCommand.MESSAGE_SUCCESS_UNDO, Messages.format(personToGroup)), expectedModel);

        // redo -> same first person edited again
        expectedModel.groupPerson(personToGroup, 2);
        assertCommandSuccess(new RedoCommand(), model,
                String.format(GroupCommand.MESSAGE_GROUP_PERSON_SUCCESS, Messages.format(personToGroup)),
                expectedModel);
    }

    @Test
    public void equals() {
        final GroupCommand standardCommand = new GroupCommand(INDEX_FIRST_PERSON, 2);

        // same values -> returns true
        GroupCommand commandWithSameValues = new GroupCommand(INDEX_FIRST_PERSON, 2);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new GroupCommand(INDEX_SECOND_PERSON, 2)));

        // different targetGroupNumber -> returns false
        assertFalse(standardCommand.equals(new GroupCommand(INDEX_FIRST_PERSON, 3)));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        int targetGroupNumber = 2;
        GroupCommand groupCommand = new GroupCommand(INDEX_FIRST_PERSON, targetGroupNumber);
        String expected = GroupCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + ", targetGroup="
                + targetGroupNumber + "}";
        assertEquals(expected, groupCommand.toString());
    }
}
