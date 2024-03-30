package seedu.address.logic.inputhistory;

import seedu.address.commons.util.QueueUtil;
import seedu.address.commons.util.RandomAccessQueue;

/**
 * Represents the history of user input.
 */
public class UserInputHistoryManager implements UserInputHistory<String> {
    private static final int MAX_HISTORY_SIZE = 100;

    /**
     * The history of user input.
     */
    private final RandomAccessQueue<String> history; // not sure if String should be used

    public UserInputHistoryManager() {
        history = new QueueUtil<>(MAX_HISTORY_SIZE);
    }

    @Override
    public void addChatToHistory(String chatMessage) {
        history.addLast(chatMessage);
    }

    @Override
    public boolean hasHistory() {
        return !history.isEmpty();
    }

    @Override
    public String getPreviousChat() {
        return history.traverseUp();
    }

    @Override
    public String getNextChat() {
        return history.traverseDown();
    }
}
