
public class CustomNode<T> {

    public T item;
    public CustomNode<T> previous;
    public CustomNode<T> next;

    public CustomNode(T item, CustomNode<T> previous, CustomNode<T> next) {
        this.item = item;
        this.previous = previous;
        this.next = next;
    }
}