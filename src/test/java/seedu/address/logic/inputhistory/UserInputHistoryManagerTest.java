package seedu.address.logic.inputhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserInputHistoryManagerTest {

    private static final String[] CHAT_HISTORY = {"Hello", "World", "How", "Are", "You"};
    private UserInputHistoryManager history;

    @BeforeEach
    void setUp() {
        history = new UserInputHistoryManager();
    }

    void addInputs() {
        for (String chat : CHAT_HISTORY) {
            history.addChatToHistory(chat);
        }
    }

    @Test
    void addChatToHistory() {
        addInputs();
        assertEquals(history.size(), CHAT_HISTORY.length);
        assertTrue(history.hasHistory());
    }

    @Test
    void getPreviousChat() {
        addInputs();
        for (int i = CHAT_HISTORY.length - 1; i >= 0; i--) {
            assertEquals(CHAT_HISTORY[i], history.getPreviousChat());
        }
        assertNull(history.getPreviousChat());
    }

    @Test
    void getNextChat() {
        addInputs();
        for (int i = CHAT_HISTORY.length - 1; i >= 0; i--) {
            history.getPreviousChat();
        }
        for (int i = 1; i < CHAT_HISTORY.length; i++) {
            assertEquals(CHAT_HISTORY[i], history.getNextChat());
        }
        assertNull(history.getNextChat());
    }

    @Test
    void getPreviousChat_emptyHistory_returnsNull() {
        assertNull(history.getPreviousChat());
    }

    @Test
    void getNextChat_emptyHistory_returnsNull() {
        assertNull(history.getNextChat());
    }

    @Test
    void hasHistory_emptyHistory_returnsFalse() {
        assertFalse(history.hasHistory());
    }
}
