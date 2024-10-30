package structures;

public interface IGraph<T>{
    public abstract void add(T item);
    public abstract void addConnection(T itemStart, T  itemEnd);
    public abstract void remove(T item);
    public abstract void removeConnection(T itemStart, T  itemEnd);
}
