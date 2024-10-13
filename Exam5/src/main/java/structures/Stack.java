package structures;


import exceptions.StackException;

public class Stack<T> implements IStack<T>{

    private TNode<T> last;

    public Stack() {
        //Default constructor
    }

    public boolean isEmpty() {
        return last == null;
    }

    public void push(T item) {
        TNode<T> newNode = new TNode<>(item);

        if(isEmpty()) {
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrev(last);
            last = newNode;
        }
    }

    public T pop() throws StackException {
        T lastElement = null;

        if(isEmpty()) {
            throw new StackException("The Stack is empty.");
        } else {
            lastElement = last.getItem();
            last = last.getPrev();
            if(last != null){
                last.setNext(null);
            }
        }

        return lastElement;
    }

    public T top() throws StackException {
        if(isEmpty()) {
            throw new StackException("Nothing is saved in the stack.");
        }

        return last.getItem();
    }

}