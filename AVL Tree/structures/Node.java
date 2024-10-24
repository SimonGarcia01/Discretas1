package structures;

public class Node<T extends Comparable<T>> {

    private T value;
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;

    public Node(T value) {
        this.value = value;
    }

    public int getBalancingFactor() {
        int leftHeight = getHeight(left);
        int rightHeight = getHeight(right);
        return rightHeight - leftHeight;
    }
    
    private int getHeight(Node<T> node) {
        if (node == null) {
            return 0; 
        }
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}