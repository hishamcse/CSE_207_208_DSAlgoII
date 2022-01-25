
/*
 * Implementation of a user defined circular doubly linked list
 */

public class CustomLinkedList<T> {

    private CustomNode<T> head, tail, pointer;
    private int length;

    // private helper methods
    private void checkValidity(CustomNode<T> node, T newItem){
        if(node==null || newItem==null) throw new IllegalArgumentException("Null Argument");
    }

    private CustomNode<T> findNode(T item){
        CustomNode<T> current = head;
        int i = 0;
        while (i < length) {
            if(item.equals(current.item)){
                return current;
            }
            current = current.next;
            i++;
        }
        return null;
    }

    // public methods
    public void insertAll(T[] items){
        if(items.length == 0) throw new IllegalArgumentException("Empty argument");

        length = items.length;

        T item = items[0];
        CustomNode<T> node = new CustomNode<>(item, null, null);
        head = node;                    // pointing head
        CustomNode<T> prev = node;
        int i = 1;
        while (i < length) {
            item = items[i];
            node = new CustomNode<>(item, prev, null);
            prev.next = node;
            prev = node;
            i++;
        }
        tail = node;
        tail.next = head;
        head.previous = tail;
        pointer = head;
    }

    public void insertBefore(T item, T newItem) {
        CustomNode<T> node = findNode(item);
        checkValidity(node,newItem);

        CustomNode<T> prev = node.previous;
        CustomNode<T> newNode = new CustomNode<>(newItem, prev, node);
        node.previous = newNode;
        prev.next = newNode;
        if (prev == tail) {
            head = newNode;
        }
        length++;
    }

    public void insertAfter(T item, T newItem) {
        CustomNode<T> node = findNode(item);
        checkValidity(node,newItem);

        CustomNode<T> next = node.next;
        CustomNode<T> newNode = new CustomNode<>(newItem, node, next);
        node.next = newNode;
        next.previous = newNode;
        if (next == head) {
            tail = newNode;
        }
        length++;
    }

    public void remove(T item){
        CustomNode<T> node = findNode(item);
        if(node==null) throw new IllegalArgumentException("No such item exists");

        CustomNode<T> prev = node.previous;
        CustomNode<T> next = node.next;
        prev.next = next;
        next.previous = prev;
        if(node == head){
            head = next;
        }
        if(node == tail){
            tail = prev;
        }
        length--;
    }

    public void setPointer(boolean reverse){
        if(reverse){
            pointer = pointer.previous;
        }else{
            pointer = pointer.next;
        }
    }

    public T getPrev(){
        pointer = pointer.previous;
        return pointer.item;
    }

    public T getNext(){
        pointer = pointer.next;
        return pointer.item;
    }

    public T getCurrent(){
        return pointer.item;
    }

    public int getLength(){
        return length;
    }
}