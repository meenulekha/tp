package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ViewCommandTest {
    @Test
    public void execute() {
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PERSON);
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        assertEquals("Viewing comment of Person: Alice Pauline; Phone: 94351253; Email: alice@example.com; "
                + "Category: PARTICIPANT\n" + "She is an Expert.", viewCommand.execute(model).getFeedbackToUser());
    }
}
