package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class UndoCommandTest {
    private Model model;

    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
    }

    @Test
    void execute_emptyHistory_fail() {
        assertCommandFailure(new UndoCommand(), model, String.format(UndoCommand.MESSAGE_FAILURE_NO_COMMAND_TO_UNDO));
    }

    @Test
    public void execute_reversibleCommandStub_success() throws CommandException {
        Command command = new ReversibleCommandStub();
        command.execute(model);
        // Stub execution has no effect on model
        assertCommandSuccess(new UndoCommand(), model, ReversibleCommandStub.MESSAGE_SUCCESS_UNDO, model);
    }

    private class ReversibleCommandStub extends Command implements ReversibleCommand {
        public static final String MESSAGE_SUCCESS = "success";
        public static final String MESSAGE_SUCCESS_UNDO = "undo";
        public static final String MESSAGE_SUCCESS_REDO = "redo";

        @Override
        public CommandResult execute(Model model) {
            model.addCommand(this);
            return new CommandResult(MESSAGE_SUCCESS);
        }

        @Override
        public CommandResult undo(Model model) {
            return new CommandResult(MESSAGE_SUCCESS_UNDO);
        }

        @Override
        public CommandResult redo(Model model) {
            return new CommandResult(MESSAGE_SUCCESS_REDO);
        }
    }
}
