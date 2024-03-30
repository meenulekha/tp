package seedu.address.commons.util;

/**
 * Represents a queue that allows random access to its elements.
 */
public interface RandomAccessQueue<E> {

    /**
     * Adds the specified element to the tail of the queue. If the queue is full,
     * the oldest element will be removed from the head of the queue. Behavior is
     * undefined if the queue has maximum capacity of 0.
     *
     * @param e the element to add
     */
    public void addLast(E e);

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue
     * is empty.
     *
     * @return the first element in the queue, or null if the queue is empty
     */
    public E pollFirst();

    /**
     * Returns the element at the specified index in the queue. The element at index
     * 0 is the head of the queue, the element at index 1 is the next element in the
     * queue, and so on.
     *
     * @param index the index of the element to return
     * @return the element at the specified index
     */
    public E get(int index);

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    public int size();

    /**
     * Traverses one step up towards the head to the next element in the queue and
     * returns it. If the head of the queue is reached, returns null.
     *
     * @return the next element in the queue, or null if the head of the queue is
     *         reached
     */
    public E traverseUp();

    /**
     * Traverses one step down towards the tail to the next element in the queue and
     * returns it. If the tail of the queue is reached, returns null.
     *
     * @return the next element in the queue, or null if the tail of the queue is
     *         reached
     */
    public E traverseDown();

    /**
     * Returns whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty();
}
