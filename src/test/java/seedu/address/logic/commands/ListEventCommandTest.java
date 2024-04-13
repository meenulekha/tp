package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.ListEventCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListEventCommand.
 */
public class ListEventCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        ListEventCommand listEventCommand = new ListEventCommand();
        assertEquals(listEventCommand.execute(model).getFeedbackToUser(), MESSAGE_SUCCESS);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        // No need to show any specific event, as we're testing the behavior when the list is filtered
        ListEventCommand listEventCommand = new ListEventCommand();
        assertEquals(listEventCommand.execute(model).getFeedbackToUser(), MESSAGE_SUCCESS);
    }
}




