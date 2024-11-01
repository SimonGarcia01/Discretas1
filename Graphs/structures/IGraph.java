package structures;

import java.util.Map;

public interface IGraph<K, T>{
    public abstract void add(K key, T item);
    public abstract void addConnection(K keyStart, K keyEnd);
    public abstract void remove(K key);
    public abstract void removeConnection(K keyStart, K keyEnd);
    public abstract void bFS(K key);
    public abstract Map<K, ListVertex<T>>  getVerticies();
}
