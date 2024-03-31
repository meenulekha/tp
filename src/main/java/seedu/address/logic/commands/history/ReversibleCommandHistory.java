package seedu.address.logic.commands.history;

import seedu.address.logic.commands.ReversibleCommand;
import seedu.address.logic.commands.exceptions.UndoException;

/**
 * Represents a history of reversible commands.
 */
public interface ReversibleCommandHistory {
    /**
     * Adds a command to the history.
     *
     * @param command The command to add.
     */
    void addCommand(ReversibleCommand command);

    /**
     * Returns the most recently executed reversible command in the history.
     *
     * @return The most recently executed reversible command.
     * @throws UndoException If an error occurs during history retrieval.
     */
    ReversibleCommand getCommandToUndo() throws UndoException;

    /**
     * Returns the most recently undone command in the history.
     *
     * @return The most recently undone command.
     * @throws UndoException If an error occurs during history retrieval.
     */
    ReversibleCommand getCommandToRedo() throws UndoException;

    /**
     * Checks if there are commands that can be undone.
     *
     * @return True if there are commands that can be undone, false otherwise.
     */
    boolean canUndo();

    /**
     * Checks if there are commands that can be redone.
     *
     * @return True if there are commands that can be redone, false otherwise.
     */
    boolean canRedo();
}
