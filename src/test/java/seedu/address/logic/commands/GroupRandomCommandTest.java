package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class GroupRandomCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_success() {
        GroupRandomCommand groupRandomCommand = new GroupRandomCommand(3);

        String expectedMessage = String.format(GroupRandomCommand.MESSAGE_GROUP_RANDOM_SUCCESS);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(groupRandomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidGroupSize_failure() {
        GroupRandomCommand groupRandomCommand = new GroupRandomCommand(0);

        assertCommandFailure(groupRandomCommand, model, GroupRandomCommand.MESSAGE_INVALID_MAX_GROUP_SIZE);
    }

    @Test
    public void executeUndoRedo_validIndex_success() throws Exception {
        GroupRandomCommand groupRandomCommand = new GroupRandomCommand(3);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        groupRandomCommand.execute(model);

        // undo -> reverts addressbook back to previous state
        assertCommandSuccess(new UndoCommand(), model,
                String.format(GroupRandomCommand.MESSAGE_SUCCESS_UNDO), expectedModel);

        // redo -> same first person edited again
        assertCommandSuccess(new RedoCommand(), model,
                String.format(GroupRandomCommand.MESSAGE_GROUP_RANDOM_SUCCESS), expectedModel);
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
