package structures;

public class TNode<T> {
    private T item;
    private TNode<T> next;
    private TNode<T> prev;

    public TNode(T item){
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public TNode<T> getNext() {
        return next;
    }

    public void setNext(TNode<T> next) {
        this.next = next;
    }

    public TNode<T> getPrev() {
        return prev;
    }

    public void setPrev(TNode<T> prev) {
        this.prev = prev;
    }
}