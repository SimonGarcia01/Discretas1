import java.util.List;

public interface ISet<T>{
    public abstract void add(T element);
    public abstract void delete(T element);
    public abstract void union(ISet<T> set);
    public abstract void intersection(ISet<T> set);
    public abstract void difference(ISet<T> set);
    public abstract void complement(ISet<T> set);
    public abstract List<T> getAllElements();
    public abstract int findElement(T element);
    public abstract boolean isSubset(ISet<T> set);
}
