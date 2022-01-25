
/*
 * User defined stack implementation for problem 1
 */

import java.util.NoSuchElementException;

public class CustomStack<T> {

    static class CustomNode<T> {

        T item;
        CustomNode<T> next;

        public CustomNode(T item, CustomNode<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private CustomNode<T> head;
    private int length;

    public CustomStack() {
        head = null;
        length = 0;
    }

    public void push(T item) {
        CustomNode<T> prevHead = head;
        head = new CustomNode<>(item, prevHead);
        length++;
    }

    public T pop() {
        if (length == 0) {
            throw new NoSuchElementException("Stack is empty");
        }
        CustomNode<T> prevHead = head;
        head = prevHead.next;
        length--;
        return prevHead.item;
    }

    public T peek() {
        if (length == 0) {
            throw new NoSuchElementException("Stack is empty");
        }
        return head.item;
    }

    public int length() {
        return length;
    }
}