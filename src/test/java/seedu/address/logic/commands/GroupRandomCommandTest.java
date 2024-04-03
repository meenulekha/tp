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
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


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
        final GroupRandomCommand standardCommand = new GroupRandomCommand(2);

        // same values -> returns true
        GroupRandomCommand commandWithSameValues = new GroupRandomCommand(2);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different maxGroupSize -> returns false
        assertFalse(standardCommand.equals(new GroupRandomCommand(3)));
    }

    @Test
    public void toStringMethod() {
        int maxGroupSize = 2;
        GroupRandomCommand groupRandomCommand = new GroupRandomCommand(maxGroupSize);
        String expected = GroupRandomCommand.class.getCanonicalName() + "{maxGroupSize=" + maxGroupSize + "}";
        assertEquals(expected, groupRandomCommand.toString());
    }
}
