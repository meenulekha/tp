package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteEventCommand}.
 */
public class DeleteEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        DeleteEventCommand deleteCommand = new DeleteEventCommand(INDEX_FIRST_EVENT);

        try {
            // Execute the delete command
            EventCommandResult commandResult = deleteCommand.execute(model);

            // Prepare the expected event list after the delete command is executed
            UniqueEventList expectedEventList = new UniqueEventList();
            expectedEventList.setEvents(model.getFilteredEventList());

            // Prepare the expected model
            Model expectedModel = new ModelManager(model.getAddressBook(),
                    model.getEventBook(), new UserPrefs());

            // Prepare the expected message
            String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete.toString());

            // Assert that the feedback message is as expected
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());

            // Assert that the filtered event list in the model matches the expected event list
            assertEquals(expectedEventList.asUnmodifiableObservableList(), model.getFilteredEventList());
        } catch (CommandException e) {
            fail("Execution of command should not fail.", e);
        }
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DeleteEventCommand deleteCommand = new DeleteEventCommand(outOfBoundIndex);

        try {
            deleteCommand.execute(model);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, e.getMessage());
        }
    }

    @Test
    public void equals() {
        DeleteEventCommand deleteFirstCommand = new DeleteEventCommand(INDEX_FIRST_EVENT);
        DeleteEventCommand deleteSecondCommand = new DeleteEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteEventCommand deleteFirstCommandCopy = new DeleteEventCommand(INDEX_FIRST_EVENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteEventCommand deleteCommand = new DeleteEventCommand(targetIndex);
        String expected = DeleteEventCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() {
        // Select the event to be deleted
        Event eventToDelete = model.getFilteredEventList().get(INDEX_SECOND_EVENT.getZeroBased());
        DeleteEventCommand deleteCommand = new DeleteEventCommand(INDEX_SECOND_EVENT);

        try {
            // Execute the delete command
            EventCommandResult deleteResult = deleteCommand.execute(model);

            // Verify that the delete operation was successful
            assertEquals(String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, Messages.formatEvent(eventToDelete)),
                    deleteResult.getFeedbackToUser());

            // Get the current state of the model
            ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());

            // Verify that the event is deleted from the model's list of events
            assertFalse(expectedModel.getFilteredEventList().contains(eventToDelete));

            // Undo the delete operation
            CommandResult undoResult = new UndoCommand().execute(model);

            // Verify that the undo operation was successful
            assertEquals(String.format(DeleteEventCommand.MESSAGE_SUCCESS_UNDO, Messages.formatEvent(eventToDelete)),
                    undoResult.getFeedbackToUser());

            // Redo the delete operation
            CommandResult redoResult = new RedoCommand().execute(model);

            // Verify that the event is deleted again from the model's list of events
            assertFalse(expectedModel.getFilteredEventList().contains(eventToDelete));

        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

}
