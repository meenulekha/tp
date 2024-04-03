package seedu.address.logic.inputhistory;

/**
 * Represents the history of user input.
 *
 * @param <T> The type of the user input.
 */
public interface UserInputHistory<T> {
    /**
     * Adds the given chat message to the chat history.
     *
     * @param chatMessage The chat message to be added to the chat history.
     */
    public void addChatToHistory(String chatMessage);

    /**
     * Returns true if there are chat messages in the chat history.
     *
     * @return True if there are chat messages in the chat history, false otherwise.
     */
    public boolean hasHistory();

    /**
     * Returns the previous chat message in the chat history.
     *
     * @return The previous chat message in the chat history.
     */
    public T getPreviousChat();

    /**
     * Returns the next chat message in the chat history.
     *
     * @return The next chat message in the chat history.
     */
    public T getNextChat();
}
