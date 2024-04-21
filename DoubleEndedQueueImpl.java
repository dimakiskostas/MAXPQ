
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class DoubleEndedQueueImpl<T> implements DoubleEndedQueue<T> {

    private int size;
    private DoubleLinkedListNode<T> head, tail;

    public DoubleEndedQueueImpl() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(T item) {
        DoubleLinkedListNode n = new DoubleLinkedListNode(item, head, null);

        if (size == 0) {
            tail = n;
        } else {
            head.previousNode = n;
        }
        head = n;

        size++;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T data = head.data;

        head = head.nextNode;

        size--;

        if (size == 0) {
            head = null;
            tail = null;
        } else {
            head.previousNode = null;
        }

        return data;
    }

    @Override
    public void addLast(T item) {
        DoubleLinkedListNode n = new DoubleLinkedListNode(item, null, tail);

        if (size == 0) {
            head = n;
        } else {
            tail.nextNode = n;
        }
        tail = n;

        size++;
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T data = tail.data;

        tail = tail.previousNode;

        size--;

        if (size == 0) {
            head = null;
            tail = null;
        } else {
            tail.nextNode = null;
        }

        return data;
    }

    @Override
    public T getFirst() {
        if (!isEmpty()) {
            return head.data;
        } else {
            throw new NoSuchElementException("getFirst failed");
        }
    }

    @Override
    public T getLast() {
        if (!isEmpty()) {
            return tail.data;
        } else {
            throw new NoSuchElementException("getLast failed");
        }
    }

    @Override
    public void printQueue(PrintStream stream) {
        DoubleLinkedListNode ptr = head;

        while (ptr != null) {
            ptr.printNode(stream);
            ptr = ptr.nextNode;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public DoubleLinkedListNode<T> getHead() {
        return head;
    }
}
