package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.ClearEventCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



public class ClearEventCommandTest {

    @Test
    public void execute_emptyEventBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertEquals(new ClearEventCommand().execute(model).getFeedbackToUser(), MESSAGE_SUCCESS);
    }

    @Test
    public void execute_nonEmptyEventBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(),
                getTypicalEventBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(),
                getTypicalEventBook(), new UserPrefs());
        expectedModel.setEventBook(new EventBook());

        assertEquals(new ClearEventCommand().execute(model).getFeedbackToUser(), MESSAGE_SUCCESS);
    }
}

