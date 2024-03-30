package seedu.address.logic.commands.history;

import java.util.ArrayDeque;
import java.util.Deque;

import seedu.address.logic.commands.ReversibleCommand;
import seedu.address.logic.commands.exceptions.UndoException;

/**
 * Represents a history of reversible commands. This class is responsible for
 * managing the history of commands that can be undone and redone.
 */
public class CommandHistoryManager implements ReversibleCommandHistory {
    private static final int MAX_SIZE = 100;
    private final Deque<ReversibleCommand> history;
    private final Deque<ReversibleCommand> future;

    /**
     * Creates a new instance of CommandHistoryManager.
     */
    public CommandHistoryManager() {
        history = new ArrayDeque<>(MAX_SIZE);
        future = new ArrayDeque<>(MAX_SIZE);
    }

    @Override
    public void addCommand(ReversibleCommand command) {
        if (history.size() == MAX_SIZE) {
            history.pollLast();
        }
        history.addFirst(command);
    }

    @Override
    public ReversibleCommand getCommandToUndo() throws UndoException {
        if (history.isEmpty()) {
            throw new UndoException("No command to undo");
        }
        ReversibleCommand command = history.pollFirst();
        future.addFirst(command);
        return command;
    }

    @Override
    public ReversibleCommand getCommandToRedo() throws UndoException {
        if (future.isEmpty()) {
            throw new UndoException("No command to redo");
        }
        ReversibleCommand command = future.pollFirst();
        history.addFirst(command);
        return command;
    }

    @Override
    public boolean canUndo() {
        return !history.isEmpty();
    }

    @Override
    public boolean canRedo() {
        return !future.isEmpty();
    }
}
