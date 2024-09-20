import java.util.ArrayList;

public interface ISet<T>{
    public abstract void add(T element);
    public abstract void delete(T element);
    public abstract ISet<T> union(ISet<T> set);
    public abstract ISet<T> intersection(ISet<T> set);
    public abstract ISet<T> difference(ISet<T> set);
    public abstract ArrayList<T> getAllElements();
    public abstract T getElement(T element);
    public abstract boolean belongs(T element);
    public abstract ISet<T> complement(ISet<T> set);
}
