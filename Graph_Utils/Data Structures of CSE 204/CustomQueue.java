
/*
 * User defined Queue implementation for problem 2
 */

import java.util.NoSuchElementException;

public class CustomQueue<T> {

    static class CustomNode<T> {

        T item;
        CustomNode<T> next;

        public CustomNode(T item, CustomNode<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private CustomNode<T> head, tail;
    private int length;

    public CustomQueue() {
        head = tail = null;
        length = 0;
    }

    public void enqueue(T item) {
        CustomNode<T> prevTail = tail;
        tail = new CustomNode<>(item, null);
        if (length == 0) {
            head = tail;
        } else {
            prevTail.next = tail;
        }
        length++;
    }

    public T dequeue() {
        if (length == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        CustomNode<T> prevHead = head;
        head = prevHead.next;
        length--;
        if (length == 0) {
            tail = null;
        }
        return prevHead.item;
    }

    public T top() {
        if (length == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        return head.item;
    }

    public int length() {
        return length;
    }
}