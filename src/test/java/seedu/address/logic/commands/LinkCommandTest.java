package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LinkCommandTest {
    @Test
    public void execute_linkCommand_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        Index[] indexes = {Index.fromZeroBased(0)};
        LinkCommand linkCommand = new LinkCommand(indexes);
        try {
            linkCommand.execute(model);
            assertEquals("Exported all selected participants", LinkCommand.MESSAGE_SUCCESS);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_linkCommand_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        Index[] indexes = {Index.fromZeroBased(999)};
        LinkCommand linkCommand = new LinkCommand(indexes);
        try {
            linkCommand.execute(model);
            fail();
        } catch (CommandException e) {
            assertEquals("The person index provided is invalid", e.getMessage());
        }
    }

    @Test
    public void duplicateIndexes() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        Index[] indexes = {Index.fromZeroBased(0), Index.fromZeroBased(0)};
        LinkCommand linkCommand = new LinkCommand(indexes);
        try {
            linkCommand.execute(model);
            fail();
        } catch (CommandException e) {
            assertEquals("Duplicate index detected", e.getMessage());
        }
    }
}
