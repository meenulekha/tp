package seedu.address.commons.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueUtilTest {
    private QueueUtil<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new QueueUtil<>(5);
    }

    @Test
    void addLast_threeItems_sizeIsThree() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        assertEquals(3, queue.size());
    }

    @Test
    void addLast_sixItems_onlyFiveItemsRemaining() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);
        queue.addLast(6);
        assertEquals(5, queue.size());
        assertEquals(2, queue.get(0));
    }

    @Test
    void pollFirst_addThreeItems_itemsOutFifo() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        assertEquals(1, queue.pollFirst());
        assertEquals(2, queue.pollFirst());
        assertEquals(3, queue.pollFirst());
        assertNull(queue.pollFirst());
        assertTrue(queue.isEmpty());
    }

    @Test
    void get_addThreeItems_correctIndex() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        assertEquals(1, queue.get(0));
        assertEquals(2, queue.get(1));
        assertEquals(3, queue.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> queue.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> queue.get(3));
    }

    @Test
    void size_addThreeItems_correctSize() {
        assertEquals(0, queue.size());
        queue.addLast(1);
        assertEquals(1, queue.size());
        queue.addLast(2);
        assertEquals(2, queue.size());
        queue.pollFirst();
        assertEquals(1, queue.size());
    }

    @Test
    void traverseUp_tailToHead_correctOrderAndNullReachingHead() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        assertEquals(3, queue.traverseUp());
        assertEquals(2, queue.traverseUp());
        assertEquals(1, queue.traverseUp());
        assertEquals(null, queue.traverseUp());
    }

    @Test
    void traverseDown_afterTraversingUp_correctOrderAndNull() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);

        queue.traverseUp();
        queue.traverseUp();
        queue.traverseUp();

        assertEquals(2, queue.traverseDown());
        assertEquals(3, queue.traverseDown());
        assertNull(queue.traverseDown());
    }

    @Test
    void isEmpty_addItemThenPoll_correctSize() {
        assertTrue(queue.isEmpty());
        queue.addLast(1);
        assertFalse(queue.isEmpty());
        queue.pollFirst();
        assertTrue(queue.isEmpty());
    }
}
