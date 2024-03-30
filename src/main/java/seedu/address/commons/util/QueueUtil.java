package seedu.address.commons.util;

public class QueueUtil<E> implements RandomAccessQueue<E> {
    private final int capacity;
    private final E[] elements;
    private int head;
    private int tail;
    private int size;

    /**
     * Current traversal index. By default, it is at the tail of the queue.
     */
    private int cur;

    /**
     * Constructs a {@code QueueUtil} with the given capacity.
     */
    public QueueUtil(int capacity) {
        this.capacity = capacity;
        @SuppressWarnings("unchecked")
        E[] temp = (E[]) new Object[capacity];
        this.elements = temp;
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.cur = 0;
    }

    @Override
    public void addLast(E e) {
        if (size == capacity) {
            head = (head + 1) % capacity;
        } else {
            size++;
        }
        elements[tail] = e;
        tail = (tail + 1) % capacity;
        cur = tail;
    }

    @Override
    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        E result = elements[head];
        head = (head + 1) % capacity;
        size--;
        return result;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[(head + index) % capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E traverseUp() {
        if (cur == head) {
            return null;
        }
        cur = (cur - 1 + capacity) % capacity;
        return elements[cur];
    }

    @Override
    public E traverseDown() {
        if (cur == tail) {
            return null;
        }
        cur = (cur + 1) % capacity;
        return elements[cur];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
