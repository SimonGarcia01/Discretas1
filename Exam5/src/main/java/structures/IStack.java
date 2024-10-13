package structures;

import exceptions.StackException;

public interface IStack<T> {
    public abstract boolean isEmpty();
    public abstract void push(T item);
    public abstract T top() throws StackException;
    public abstract T pop() throws StackException;
}